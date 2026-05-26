package com.example.chatbot.homeScreen.presentation

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatbot.ads.BannerAdView
import com.example.chatbot.homeScreen.viewmodel.ChatViewModel
import com.example.chatbot.homeScreen.viewmodel.ChatViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {

    val context =
        LocalContext.current

    val vm: ChatViewModel =
        viewModel(

            factory =

                ChatViewModelFactory(

                    context.applicationContext
                            as Application
                )
        )

    var text by remember {
        mutableStateOf("")
    }

    val listState =
        rememberLazyListState()

    LaunchedEffect(vm.messages.size) {

        if (vm.messages.isNotEmpty()) {

            listState.animateScrollToItem(
                vm.messages.lastIndex
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {



        TopAppBar(

            modifier = Modifier
                .statusBarsPadding(),

            title = {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "🤖"
                    )

                    Text(
                        text = " ChatBot"
                    )
                }
            },
            actions = {

                Text(

                    text = "New Chat",

                    color = Color.Blue,

                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable {

                            vm.newChat()
                        }
                )
            },

            colors = TopAppBarDefaults
                .topAppBarColors(
                    containerColor =
                        Color.White
                )
        )




        HorizontalDivider()



        LazyColumn(

            state = listState,

            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),

            contentPadding =
                PaddingValues(
                    12.dp
                ),

            verticalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {

            items(vm.messages) { message ->

                MessageBubble(
                    message = message
                )
            }

            if (vm.isTyping.value) {

                item {

                    TypingIndicator()
                }
            }
        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .navigationBarsPadding()
                .imePadding()
        ) {

            Column {
                BannerAdView()
                HorizontalDivider()

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 10.dp,
                            vertical = 8.dp
                        ),

                    verticalAlignment =
                        Alignment.Bottom
                ) {

                    OutlinedTextField(

                        value = text,

                        onValueChange = {

                            text = it
                        },

                        modifier = Modifier
                            .weight(1f),

                        placeholder = {

                            Text(
                                text = "Ask anything..."
                            )
                        },

                        shape = RoundedCornerShape(25.dp),

                        maxLines = 4,

                        keyboardOptions =
                            KeyboardOptions(
                                imeAction =
                                    ImeAction.Send
                            ),

                        colors =
                            TextFieldDefaults.colors(

                                focusedContainerColor =
                                    Color(0xFFF5F5F5),

                                unfocusedContainerColor =
                                    Color(0xFFF5F5F5),

                                focusedIndicatorColor =
                                    Color.Transparent,

                                unfocusedIndicatorColor =
                                    Color.Transparent
                            )
                    )

                    IconButton(

                        onClick = {

                            val message =
                                text.trim()

                            if (message.isNotEmpty()) {

                                vm.sendMessage(message)

                                text = ""
                            }
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.Send,

                            contentDescription =
                                "Send",

                            tint = Color.Blue
                        )
                    }
                }
            }
        }
    }
}