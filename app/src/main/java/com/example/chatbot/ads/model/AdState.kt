package com.example.chatbot.ads.model


sealed class AdState {

    object Idle : AdState()

    object Loading : AdState()

    object Loaded : AdState()

    object Failed : AdState()

    object Showing : AdState()

    object Dismissed : AdState()
}