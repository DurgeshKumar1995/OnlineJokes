package com.online.jokes.persentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.online.jokes.data.model.JokeModel
import com.online.jokes.ui.theme.BackGround
import com.online.jokes.ui.theme.Purple40
import com.online.jokes.ui.theme.Typography
import com.online.jokes.utils.DateUtils

@Composable
fun JokeItemScreen(
    modifier: Modifier = Modifier,
    model: JokeModel,
) {
    Surface(
        color = BackGround,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .background(color = Purple40, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .padding(top = 10.dp, start = 6.dp, end = 10.dp, bottom = 5.dp),
        ) {
            Text(
                text = model.joke,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 45.dp),
                style = Typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = DateUtils.getReadableTime(model.time),
                modifier = Modifier.fillMaxWidth(),
                style = Typography.labelSmall,
            )
        }
    }
}
