package com.example.chatapp.ui.shared

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.chatapp.R
import com.example.chatapp.data.models.Message
import com.example.chatapp.ui.themes.activeColor
import com.example.chatapp.ui.themes.inactiveColor
import com.example.chatapp.ui.themes.inactiveTextColor
import kotlinx.coroutines.delay

@Composable
fun MessageItem(message: Message, isFromSameUser: Boolean, isLastItem: Boolean) {
    val alignment = if (isFromSameUser) Alignment.CenterEnd else Alignment.CenterStart
    val shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp)
    var hasSeen by remember { mutableStateOf(if (!isLastItem) true else null) }

    if (isLastItem) {
        LaunchedEffect(message) {
            delay(1000)
            hasSeen = false
            delay(1000)
            hasSeen = true
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = alignment
    ) {
        Text(
            text = message.content,
            color = if (isFromSameUser) Color.White else inactiveTextColor,
            modifier = Modifier
                .background(
                    if (isFromSameUser) activeColor else inactiveColor,
                    shape = if (isFromSameUser)
                        shape.copy(bottomEnd = CornerSize(0.dp))
                    else
                        shape.copy(bottomStart = CornerSize(0.dp))
                )
                .padding(horizontal = 12.dp)
                .padding(top = 8.dp, bottom = 11.dp)
        )
        Icon(
            painterResource(id = R.drawable.ic_double_tick_24),
            contentDescription = "Done",
            tint = animateColorAsState(
                targetValue = if (hasSeen == true) Color(0xFFFFC371) else inactiveColor,
                animationSpec = tween(300, easing = FastOutLinearInEasing),
                label = "tickColor"
            ).value,
            modifier = Modifier
                .padding(bottom = 3.dp, end = 6.dp)
                .size(14.dp)
                .align(Alignment.BottomEnd)
                .alpha(
                    animateFloatAsState(
                        targetValue = if (isFromSameUser && hasSeen != null) 1f else 0f,
                        animationSpec = tween(200, easing = LinearEasing),
                        label = "tickAlpha"
                    ).value
                )
        )
    }
}