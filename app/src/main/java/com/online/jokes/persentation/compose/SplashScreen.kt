package com.online.jokes.persentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.online.jokes.R
import com.online.jokes.ui.theme.Purple40
import com.online.jokes.ui.theme.Typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onRedirectScreen: () -> Unit = {},
) {
    val coroutine = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        coroutine.launch {
            delay(1000)
            onRedirectScreen.invoke()
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Purple40),
    ) {
        Text(
            stringResource(id = R.string.app_name),
            style = Typography.titleLarge,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
