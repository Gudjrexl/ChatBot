package com.example.chatbot.homeScreen.data.remote

import com.example.chatbot.homeScreen.data.model.GeminiRequest
import com.example.chatbot.homeScreen.data.model.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GeminiApi {

    @POST(
        "v1beta/models/gemini-flash-latest:generateContent"
    )

    suspend fun generateContent(

        @Header("X-goog-api-key")
        apiKey: String,

        @Body
        request: GeminiRequest

    ): GeminiResponse
}