package com.example.chatapp.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatapp.R
import com.example.chatapp.ui.screens.chat.ChatViewModel
import com.example.chatapp.ui.themes.activeColor
import com.example.chatapp.ui.themes.defaultGradient
import com.example.chatapp.ui.themes.inactiveTextColor
import com.example.chatapp.ui.themes.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppBar(viewModel: ChatViewModel) {
    val receiver by viewModel.receiver.observeAsState()
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = Modifier.customShadow(offsetX = 6.dp, blurRadius = 6.dp),
        colors = topAppBarColors(containerColor = primaryColor),
        navigationIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    enabled = false,
                    modifier = Modifier.size(48.dp),
                    onClick = { }) {
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
                            brush = defaultGradient
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Send",
                        tint = Color.White
                    )
                }
            }
        },
        title = {
            Text(
                receiver!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
        },
        actions = {
            IconButton(modifier = Modifier.size(48.dp), onClick = { expanded = true }) {
                Icon(
                    painterResource(id = R.drawable.ic_more_horiz_24),
                    contentDescription = "More",
                    tint = inactiveTextColor.copy(0.4f),
                    modifier = Modifier.size(48.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(
                    0.dp,
                    (-10).dp
                )
            ) {
                DropdownMenuItem(
                    text = { Text("Switch User") },
                    onClick = {
                        viewModel.switchUser()
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = null,
                            tint = activeColor
                        )
                    },
                )
            }
        })
}