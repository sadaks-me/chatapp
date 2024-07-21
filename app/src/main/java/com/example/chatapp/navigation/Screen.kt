package com.example.chatapp.navigation

sealed class Screen(val route: String) {
    data object ChatList : Screen("chat_list")
    data object Chat : Screen("chat")
}