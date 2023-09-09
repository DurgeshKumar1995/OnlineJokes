package com.online.jokes.persentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.online.jokes.ui.theme.Purple40

@Composable
fun DialogContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(76.dp)
            .background(color = Color.Transparent, shape = RoundedCornerShape(12.dp)),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Purple40,
        )
    }
}
