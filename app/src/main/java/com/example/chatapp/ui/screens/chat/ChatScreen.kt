package com.example.chatapp.ui.screens.chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatapp.R
import com.example.chatapp.data.models.Message
import com.example.chatapp.ui.themes.activeColor
import com.example.chatapp.ui.themes.inactiveColor
import com.example.chatapp.ui.themes.inactiveTextColor
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(user: String, viewModel: ChatViewModel, navController: NavController) {
    val messageList by viewModel.allMessages.observeAsState(initial = emptyList())
    var messageText by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding(), containerColor = Color.White, topBar = {
        TopAppBar(
            modifier = Modifier
                .shadow(4.dp)
                .background(Color.White),
            colors = topAppBarColors(containerColor = Color.White),
            navigationIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        modifier = Modifier.size(48.dp),
                        onClick = { navController.popBackStack() }) {
                        Icon(
                            painterResource(id = R.drawable.ic_chevron_left_24),
                            contentDescription = "Localized description",
                            tint = activeColor,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFFF5F6D),
                                        Color(0xFFFF5F6D),
                                        Color(0xFFFFC371)
                                    )
                                )
                            ), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Send",
                            tint = Color.White
                        )
                    }
                }
            }, title = {
                Text(
                    user, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            })
    }, bottomBar = {
        Box(
            Modifier
                .shadow(4.dp)
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Row {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .weight(0.8f)
                        .defaultMinSize(minHeight = 48.dp)
                        .imePadding()
                        .background(Color.Transparent, shape = RoundedCornerShape(32.dp))
                        .border(
                            BorderStroke(1.dp, Color.Gray.copy(0.8f)),
                            shape = RoundedCornerShape(32.dp)
                        ),
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Send
                    ),
                    shape = RoundedCornerShape(32.dp),
                    maxLines = 5
                )
                Box(Modifier.weight(0.2f), contentAlignment = Alignment.Center) {
                    IconButton(modifier = Modifier
                        .size(48.dp)
                        .alpha(if (messageText.isNotEmpty()) 1f else 0.3f)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFFF5F6D),
                                    Color(0xFFFF5F6D),
                                    Color(0xFFFFC371)
                                )
                            )
                        ), enabled = messageText.isNotEmpty(), onClick = {
                        viewModel.insert(
                            Message(
                                content = messageText,
                                timestamp = Instant.now().toEpochMilli(),
                                user = user
                            )
                        )
                        messageText = ""
                        viewModel.simulateOtherUserMessage(if (user == "Sarah") "Alice" else "Sarah")
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp)
        ) {
            itemsIndexed(messageList) { index, message ->
                MessageItem(message, isFromSameUser = message.user == user)
                val lessSpace =
                    index < messageList.size - 1 && messageList[index + 1].user == user &&
                            messageList[index + 1].timestamp - message.timestamp < 20000
                if (lessSpace) {
                    Spacer(modifier = Modifier.height(4.dp))
                } else {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Message, isFromSameUser: Boolean) {
    val alignment = if (isFromSameUser) Alignment.CenterEnd else Alignment.CenterStart
    Box(
        modifier = Modifier
            .fillMaxWidth(), contentAlignment = alignment
    ) {
        Text(
            text = message.content,
            color = if (isFromSameUser) Color.White else inactiveTextColor,
            modifier = Modifier
                .background(
                    if (isFromSameUser) activeColor else inactiveColor,
                    shape = if (isFromSameUser) RoundedCornerShape(
                        16.dp,
                        16.dp,
                        0.dp,
                        16.dp
                    ) else RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp)
                )
                .padding(8.dp)
        )
    }
}