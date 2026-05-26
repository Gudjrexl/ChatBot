package com.example.chatbot.homeScreen.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL =
        "https://generativelanguage.googleapis.com/"

    private val logging =

        HttpLoggingInterceptor().apply {

            level =
                HttpLoggingInterceptor.Level.BODY
        }

    private val client =

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    val api: GeminiApi by lazy {

        Retrofit.Builder()

            .baseUrl(BASE_URL)

            .client(client)

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()

            .create(GeminiApi::class.java)
    }
}