package com.example.chatapp.ui.screens.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.local.database.MessageDatabase
import com.example.chatapp.data.models.Message
import com.example.chatapp.data.repositories.MessageRepository
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MessageRepository
    val allMessages: LiveData<List<Message>>

    init {
        val messageDao = MessageDatabase.getDatabase(application).messageDao()
        repository = MessageRepository(messageDao)
        allMessages = repository.allMessages.asLiveData()
    }

    fun insert(message: Message) = viewModelScope.launch {
        repository.insert(message)
    }
}