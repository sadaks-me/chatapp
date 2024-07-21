package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.chatapp.data.models.Message
import com.example.chatapp.ui.screens.chat.ChatListScreen
import com.example.chatapp.ui.theme.ChatAppTheme
import java.time.Instant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatListScreen(
                        modifier = Modifier.padding(innerPadding),
                        listOf(Message(1, "Hello", Instant.now().toEpochMilli(), true), Message(1, "Hello", Instant.now().toEpochMilli(), false))
                    )
                }
            }
        }
    }
}