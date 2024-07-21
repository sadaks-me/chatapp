package com.example.chatapp.data.repositories

import androidx.annotation.WorkerThread
import com.example.chatapp.data.local.MessageDao
import com.example.chatapp.data.models.Message
import kotlinx.coroutines.flow.Flow

class MessageRepository(private val messageDao: MessageDao) {

    val allMessages: Flow<List<Message>> = messageDao.getAllMessages()

    @WorkerThread
    suspend fun insert(message: Message) {
        messageDao.insertMessage(message)
    }
}