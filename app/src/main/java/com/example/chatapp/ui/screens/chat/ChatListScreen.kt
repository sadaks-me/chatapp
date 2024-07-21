package com.example.chatapp.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chatapp.data.models.Message
import java.time.Instant

@Composable
fun ChatListScreen(viewModel: ChatViewModel) {
    val messageList by viewModel.allMessages.observeAsState(initial = emptyList())
    var messageText by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(messageList) { message ->
                    MessageItem(message)
                }
            }
            Row {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.fillMaxWidth(fraction = 0.8f)
                )
                Button(onClick = {
                    if (messageText.isNotEmpty()) {
                        viewModel.insert(
                            Message(content = messageText, timestamp = Instant.now().toEpochMilli(), isSent = true)
                        )
                        messageText = ""
                    }
                }) {
                    Text("Send")
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    val alignment = if (message.isSent) Alignment.CenterEnd else Alignment.CenterStart
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = alignment) {
        Text(
            text = message.content,
            modifier = Modifier
                .padding(8.dp)
                .background(Color.LightGray)
                .padding(8.dp)
        )
    }
}