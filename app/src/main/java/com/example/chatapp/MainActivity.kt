package com.example.chatapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatapp.ui.screens.chat.ChatScreen
import com.example.chatapp.ui.screens.chat.ChatViewModel
import com.example.chatapp.ui.themes.ChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        enableEdgeToEdge()
        setContent {
            ChatAppTheme {
                val viewModel: ChatViewModel = viewModel()
                ChatScreen(viewModel)
            }
        }
    }
}