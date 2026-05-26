package com.example.chatbot.homeScreen.viewmodel


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChatViewModelFactory(
    private val application:
    Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel>
            create(
        modelClass: Class<T>
    ): T {

        return ChatViewModel(
            application
        ) as T
    }
}