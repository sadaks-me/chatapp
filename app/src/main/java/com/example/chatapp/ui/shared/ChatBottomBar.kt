package com.example.chatapp.ui.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.chatapp.data.models.Message
import com.example.chatapp.ui.screens.chat.ChatViewModel
import com.example.chatapp.ui.themes.activeColor
import com.example.chatapp.ui.themes.defaultGradient
import java.time.Instant

@Composable
fun ChatBottomBar(user: String, viewModel: ChatViewModel) {
    var messageText by remember { mutableStateOf("") }
    BasicTextField(
        value = messageText,
        onValueChange = { messageText = it },
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Send
        ),
        maxLines = 5,
        cursorBrush = defaultGradient,
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .customShadow(color = Color.Black.copy(0.15f), offsetX = (-12).dp, blurRadius = 12.dp)
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .imePadding(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .padding(end = 12.dp)
                        .weight(0.87f)
                        .defaultMinSize(minHeight = 48.dp)
                        .background(Color.Transparent, shape = RoundedCornerShape(32.dp))
                        .border(
                            BorderStroke(
                                1.dp,
                                if (messageText.isNotEmpty()) activeColor else Color.Gray.copy(0.8f)
                            ),
                            shape = RoundedCornerShape(32.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                }
                Box(Modifier.weight(0.13f), contentAlignment = Alignment.Center) {
                    IconButton(modifier = Modifier
                        .size(48.dp)
                        .alpha(if (messageText.isNotEmpty()) 1f else 0.3f)
                        .clip(CircleShape)
                        .background(brush = defaultGradient),
                        enabled = messageText.isNotEmpty(),
                        onClick = {
                            viewModel.insert(
                                Message(
                                    content = messageText.trim(),
                                    timestamp = Instant.now().toEpochMilli(),
                                    user = user
                                )
                            )
                            messageText = ""
                            viewModel.simulateOtherUserMessage()
                        }) {
                        Icon(
                            Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = Color.White
                        )
                    }
                }
            }
        },
    )
}