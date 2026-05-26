package com.example.chatbot.homeScreen.repo

import android.util.Log
import com.example.chatbot.homeScreen.data.model.Content
import com.example.chatbot.homeScreen.data.model.GeminiRequest
import com.example.chatbot.homeScreen.data.model.Part
import com.example.chatbot.homeScreen.data.remote.ApiKey
import com.example.chatbot.homeScreen.data.remote.RetrofitInstance

class ChatRepositoryImpl : ChatRepository {

    companion object {

        private const val TAG =
            "GEMINI_DEBUG"
    }

    override suspend fun sendMessage(
        message: String
    ): Result<String> {

        return try {

            Log.d(TAG, "====================")
            Log.d(TAG, "SEND MESSAGE START")
            Log.d(TAG, "User Message : $message")

            Log.d(
                TAG,
                "API KEY : ${ApiKey.GEMINI_API_KEY}"
            )

            val request = GeminiRequest(

                contents = listOf(

                    Content(

                        parts = listOf(

                            Part(message)
                        )
                    )
                )
            )

            Log.d(
                TAG,
                "REQUEST BODY : $request"
            )

            Log.d(
                TAG,
                "CALLING GEMINI API..."
            )

            val response =
                RetrofitInstance.api.generateContent(

                    apiKey =
                        ApiKey.GEMINI_API_KEY,

                    request =
                        request
                )

            Log.d(
                TAG,
                "RAW RESPONSE : $response"
            )

            Log.d(
                TAG,
                "CANDIDATES SIZE : ${response.candidates.size}"
            )

            val aiText =
                response
                    .candidates[0]
                    .content
                    .parts[0]
                    .text

            Log.d(
                TAG,
                "AI RESPONSE : $aiText"
            )

            Log.d(
                TAG,
                "===================="
            )

            Result.success(aiText)

        } catch (e: Exception) {

            Log.e(
                TAG,
                "ERROR OCCURRED"
            )

            Log.e(
                TAG,
                "ERROR MESSAGE : ${e.message}"
            )

            Log.e(
                TAG,
                "LOCALIZED : ${e.localizedMessage}"
            )

            Log.e(
                TAG,
                "CAUSE : ${e.cause}"
            )

            e.printStackTrace()

            Result.failure(e)
        }
    }
}