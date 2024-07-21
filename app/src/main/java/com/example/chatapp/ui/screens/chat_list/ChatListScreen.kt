package com.example.chatapp.ui.screens.chat_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatapp.R
import com.example.chatapp.data.models.User
import com.example.chatapp.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(navController: NavController) {
    val users = listOf(User("Sarah"), User("Alice"))

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Text(
                LocalContext.current.getString(R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        })
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                itemsIndexed(users) { index, user ->
                    Column {
                        UserItem(user, navController)
                        if (index < users.size - 1)
                            HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User, navController: NavController) {
    ListItem(
        modifier = Modifier.clickable {
            navController.navigate("${Screen.Chat.route}/${user.name}")
        },
        headlineContent = { Text(user.name) },
        leadingContent = {
            Icon(
                Icons.Filled.Person,
                contentDescription = user.name,
            )
        }
    )
}