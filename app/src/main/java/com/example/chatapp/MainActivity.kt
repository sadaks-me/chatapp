package com.example.chatapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.navigation.Screen
import com.example.chatapp.ui.screens.chat.ChatScreen
import com.example.chatapp.ui.screens.chat.ChatViewModel
import com.example.chatapp.ui.screens.chat_list.ChatListScreen
import com.example.chatapp.ui.themes.ChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        enableEdgeToEdge()
        setContent {
            ChatAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.ChatList.route) {
                    composable(Screen.ChatList.route) {
                        ChatListScreen(navController)
                    }
                    composable("${Screen.Chat.route}/{user}") { backStackEntry ->
                        val user = backStackEntry.arguments?.getString("user") ?: return@composable
                        val viewModel: ChatViewModel = viewModel(key = user)
                        ChatScreen(user, viewModel, navController)
                    }
                }
            }
        }
    }
}