package com.example.chatapp.ui.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.chatapp.ui.themes.dateTextColor
import com.example.chatapp.utils.DateUtils.Companion.formatTimestamp

@Composable
fun TimeStampText(timestamp: Long, showTimestamp: Boolean) {
    if (showTimestamp) {
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = formatTimestamp(timestamp).first,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = dateTextColor,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = formatTimestamp(timestamp).second,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal
                ),
                color = dateTextColor,
            )
        }
    }
}