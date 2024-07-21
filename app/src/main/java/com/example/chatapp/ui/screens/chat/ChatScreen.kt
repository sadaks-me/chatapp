package com.example.chatapp.ui.screens.chat

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.chatapp.ui.shared.ChatAppBar
import com.example.chatapp.ui.shared.ChatBottomBar
import com.example.chatapp.ui.shared.MessageItem
import com.example.chatapp.ui.shared.TimeStampText
import com.example.chatapp.ui.shared.keyboardAsState
import com.example.chatapp.ui.themes.primaryColor
import kotlinx.coroutines.delay

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    val listState = rememberLazyListState()
    val focusManager = LocalFocusManager.current
    val messageList by viewModel.allMessages.observeAsState(initial = emptyList())
    val sender by viewModel.sender.observeAsState()
    val isKeyboardOpened by keyboardAsState()

    LaunchedEffect(messageList.size, isKeyboardOpened) {
        delay(150)
        if (messageList.size > 1) {
            listState.scrollToItem(messageList.size - 1)
        }
    }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        },
        containerColor = primaryColor,
        topBar = { ChatAppBar(viewModel) },
        bottomBar = {
            ChatBottomBar(sender!!, viewModel)
        }) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp)
        ) {
            itemsIndexed(messageList) { index, message ->
                val previousMessage = messageList.getOrNull(index - 1)
                val nextMessage = messageList.getOrNull(index + 1)
                val lessSpace =
                    nextMessage != null && (message.user == sender && nextMessage.user == sender) &&
                            (nextMessage.timestamp - message.timestamp) < 20000
                val showTimestamp =
                    previousMessage == null || (message.timestamp - previousMessage.timestamp) > 3600000L
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TimeStampText(timestamp = message.timestamp, showTimestamp = showTimestamp)
                    MessageItem(
                        message,
                        isFromSameUser = message.user == sender,
                        isLastItem = nextMessage == null
                    )
                    if (nextMessage != null)
                        Spacer(modifier = Modifier.height(if (lessSpace) 4.dp else 12.dp))
                }
            }
        }
    }
}