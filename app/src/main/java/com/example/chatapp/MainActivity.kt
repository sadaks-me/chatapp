package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatapp.ui.screens.chat.ChatListScreen
import com.example.chatapp.ui.screens.chat.ChatViewModel
import com.example.chatapp.ui.theme.ChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppTheme {
                val viewModel: ChatViewModel = viewModel()
                ChatListScreen(
                    viewModel
                )
            }
        }
    }
}