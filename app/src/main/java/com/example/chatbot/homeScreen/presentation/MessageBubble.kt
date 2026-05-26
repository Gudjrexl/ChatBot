package com.example.chatbot.homeScreen.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chatbot.homeScreen.data.model.ChatMessage

@Composable
fun MessageBubble(
    message: ChatMessage
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),

        horizontalArrangement =

            if (message.isUser)
                Arrangement.End
            else
                Arrangement.Start
    ) {

        Text(

            text = message.message,

            modifier = Modifier
                .background(

                    if (message.isUser)
                        Color.Blue
                    else
                        Color(0xFFC8E6C9),

                    RoundedCornerShape(16.dp)
                )
                .padding(
                    horizontal = 14.dp,
                    vertical = 10.dp
                ),

            color =

                if (message.isUser)
                    Color.White
                else
                    Color.Black
        )
    }
}