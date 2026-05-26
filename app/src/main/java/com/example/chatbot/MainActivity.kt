package com.example.chatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.chatbot.ads.AppOpenManager
import com.example.chatbot.homeScreen.presentation.ChatScreen
import com.example.chatbot.ui.theme.ChatBotTheme

class MainActivity : ComponentActivity() {

    private lateinit var appOpenManager: AppOpenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appOpenManager = AppOpenManager(this)

        appOpenManager.loadAd()
        setContent {
            ChatBotTheme {
                ChatScreen()
            }
        }
    }


}