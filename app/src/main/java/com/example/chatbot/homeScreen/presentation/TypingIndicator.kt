package com.example.chatbot.homeScreen.presentation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TypingIndicator() {

    Text(
        text = "Typing...",
        modifier = Modifier
            .padding(8.dp),
        color = Color.Gray
    )
}