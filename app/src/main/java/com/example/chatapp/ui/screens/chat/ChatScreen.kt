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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.navigation.NavController
import com.example.chatapp.data.models.Message
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(user: String, viewModel: ChatViewModel, navController: NavController) {
    val messageList by viewModel.allMessages.observeAsState(initial = emptyList())
    var messageText by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description",
                )
            }
        }, title = {
            Text(
                user,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        })
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(messageList) { message ->
                    MessageItem(message, isFromSameUser = message.user == user)
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
                            Message(
                                content = messageText,
                                timestamp = Instant.now().toEpochMilli(),
                                user = user
                            )
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
fun MessageItem(message: Message, isFromSameUser: Boolean) {
    val alignment = if (isFromSameUser) Alignment.CenterEnd else Alignment.CenterStart
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