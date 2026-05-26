package com.example.chatbot.homeScreen.viewmodel



import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.ads.model.AdState
import com.example.chatbot.ads.repoAds.BannerRepository
import com.example.chatbot.ads.repoAds.BannerRepositoryImpl
import com.example.chatbot.homeScreen.data.local.ChatDatabase
import com.example.chatbot.homeScreen.data.local.ChatEntity
import com.example.chatbot.homeScreen.data.model.ChatMessage
import com.example.chatbot.homeScreen.repo.ChatRepository
import com.example.chatbot.homeScreen.repo.ChatRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository:
            ChatRepository =
        ChatRepositoryImpl()

    private val dao =

        ChatDatabase
            .getDatabase(application)
            .chatDao()

    var messages =
        mutableStateListOf<ChatMessage>()

    var isTyping =
        mutableStateOf(false)

    init {

        loadChats()
    }

    private fun loadChats() {

        viewModelScope.launch {

            val savedChats =
                dao.getAllChats()

            messages.addAll(

                savedChats.map {

                    ChatMessage(

                        message = it.message,

                        isUser = it.isUser
                    )
                }
            )
        }
    }

    fun sendMessage(
        message: String
    ) {

        if (message.isBlank()) return

        val userMessage =

            ChatMessage(
                message = message,
                isUser = true
            )

        messages.add(userMessage)

        viewModelScope.launch {

            dao.insertChat(

                ChatEntity(

                    message = message,

                    isUser = true
                )
            )

            isTyping.value = true

            val result =
                repository.sendMessage(message)

            result.onSuccess {

                val botMessage =

                    ChatMessage(
                        message = it,
                        isUser = false
                    )

                messages.add(botMessage)

                dao.insertChat(

                    ChatEntity(

                        message = it,

                        isUser = false
                    )
                )
            }

            result.onFailure {

                messages.add(

                    ChatMessage(

                        message =
                            "Error : ${it.message}",

                        isUser = false
                    )
                )
            }

            isTyping.value = false
        }
    }





    fun newChat() {

        viewModelScope.launch {

            dao.clearChats()

            messages.clear()
        }
    }










}