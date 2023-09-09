package com.online.jokes.persentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.online.jokes.R
import com.online.jokes.persentation.MainViewModel
import com.online.jokes.ui.theme.BackGround
import com.online.jokes.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
) {
    val loading = viewModel.stateLoading.collectAsState().value
    val list = viewModel.jokes.collectAsState().value
    LifeCycleEvents {
        when (it) {
            Lifecycle.Event.ON_START -> viewModel.fetchJoke()
            Lifecycle.Event.ON_STOP -> viewModel.stopFetchJoke()
            else -> {}
        }
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = BackGround,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = Typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            list.asReversed().forEachIndexed { index, jokeModel ->
                JokeItemScreen(model = jokeModel, modifier = Modifier)
            }
        }
    }
    if (loading) {
        LoadingDialog(dismissOnClickOutSide = false, modifier = Modifier)
    }
}
