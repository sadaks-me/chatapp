package com.example.chatapp.ui.screens.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.local.database.MessageDatabase
import com.example.chatapp.data.models.Message
import com.example.chatapp.data.repositories.MessageRepository
import com.example.chatapp.utils.SentenceGenerator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MessageRepository
    val allMessages: LiveData<List<Message>>
    var sender = MutableLiveData("Alice")
    var receiver = MutableLiveData("Sarah")

    init {
        val messageDao = MessageDatabase.getDatabase(application).messageDao()
        repository = MessageRepository(messageDao)
        allMessages = repository.allMessages.asLiveData()
    }

    fun insert(message: Message) = viewModelScope.launch {
        repository.insert(message)
    }

    fun simulateOtherUserMessage() = viewModelScope.launch {
        delay(3000)
        val message = Message(
            content = SentenceGenerator.generate(),
            timestamp = Instant.now().toEpochMilli(),
            user = receiver.value!!,
        )
        repository.insert(message)
    }

    fun switchUser() {
        val s = receiver.value
        val r = sender.value
        sender.postValue(s)
        receiver.postValue(r)
    }
}