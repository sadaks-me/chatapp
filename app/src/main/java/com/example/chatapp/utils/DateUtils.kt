package com.example.chatapp.utils

import java.text.SimpleDateFormat
import java.util.*


class DateUtils {
    companion object {
        fun formatTimestamp(timestamp: Long): Pair<String, String> {
            val default = Locale.getDefault()
            return Pair(
                SimpleDateFormat("EEEE", default).format(Date(timestamp)),
                SimpleDateFormat("HH:mm", default).format(Date(timestamp))
            )
        }
    }
}