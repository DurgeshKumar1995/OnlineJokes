package com.online.jokes.persentation

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.online.jokes.data.model.JokeModel
import com.online.jokes.data.repo.ToastRepo
import com.online.jokes.domain.AddJokeListLocal
import com.online.jokes.domain.GetJoke
import com.online.jokes.domain.GetJokeListLocal
import com.online.jokes.domain.ToastCase
import com.online.jokes.utils.Constants
import com.online.jokes.utils.network.Status
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.ConcurrentLinkedQueue

class MainViewModel(
    private val jokeCase: GetJoke,
    private val localAddJoke: AddJokeListLocal,
    private val localGetJoke: GetJokeListLocal,
    private val toastCase: ToastCase,
    private val ioDispatcher: CoroutineDispatcher,
    private val mainDispatcher: MainCoroutineDispatcher,
) : ViewModel() {

    private val _jokes = MutableStateFlow<List<JokeModel>>(emptyList())
    val jokes: StateFlow<List<JokeModel>> = _jokes.asStateFlow()

    private val _stateLoading = MutableStateFlow(false)
    val stateLoading: StateFlow<Boolean> = _stateLoading.asStateFlow()

    private val queue by lazy { ConcurrentLinkedQueue<JokeModel>() }

    init {
        viewModelScope.launch {
            _stateLoading.value = true
            withContext(ioDispatcher) {
                queue.clear()
                val local = localGetJoke.invoke().first()
                queue.addAll(local)
                Timber.tag(Tag).d("Joke::Local::${local.firstOrNull()}")
                withContext(mainDispatcher) {
                    _stateLoading.value = false
                    _jokes.tryEmit(queue.toList())
                }
            }
        }
    }

    private var fetchJokeJob: Job? = null

    fun fetchJoke() {
        fetchJokeJob = viewModelScope.launch(mainDispatcher) {
            jokeCase.invoke().collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        _stateLoading.value = false
                        Timber.tag(Tag).d("Joke::${it.data}")
                        it.data?.run(::add)
                    }

                    Status.ERROR -> {
                        _stateLoading.value = false
                        showToast(it.message)
                    }

                    Status.LOADING -> {
                        _stateLoading.value = true
                    }
                }
            }
        }
    }

    fun stopFetchJoke() {
        fetchJokeJob?.cancel()
        fetchJokeJob = null
    }

    private fun add(data: JokeModel) {
        queue.add(data)
        while (queue.size > LIST_ITEM_SIZE_LIMIT) {
            queue.poll()
        }
        val list = queue.toList()
        _jokes.tryEmit(list)
        viewModelScope.launch(ioDispatcher) {
            val status = localAddJoke.invoke(list).first()
            Timber.tag(Tag).d("add::status:$status")
        }
    }

    private fun showToast(message: String?, @ToastRepo.Duration time: Int = Toast.LENGTH_SHORT) {
        toastCase.invoke(message ?: Constants.EMPTY, time)
    }

    companion object {
        private const val Tag = "MainViewModel"
        private const val LIST_ITEM_SIZE_LIMIT = 10
    }
}
