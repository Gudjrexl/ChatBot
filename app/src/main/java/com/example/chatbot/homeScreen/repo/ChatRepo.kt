package com.example.chatbot.homeScreen.repo

interface ChatRepository {

    suspend fun sendMessage(
        message: String
    ): Result<String>
}