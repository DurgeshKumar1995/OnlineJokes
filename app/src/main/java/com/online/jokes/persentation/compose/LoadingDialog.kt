package com.online.jokes.persentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    isShowLoading: Boolean = true,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutSide: Boolean = false,
    onDismiss: () -> Unit = {},
) {
    if (isShowLoading) {
        Dialog(
            onDismissRequest = {
                onDismiss.invoke()
            },
            DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutSide,
            ),
        ) {
            DialogContent(modifier = modifier)
        }
    }
}
