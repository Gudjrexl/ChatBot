package com.example.chatbot.homeScreen.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChatDao {

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )

    suspend fun insertChat(
        chat: ChatEntity
    )

    @Query(
        "SELECT * FROM chat_table"
    )

    suspend fun getAllChats():
            List<ChatEntity>

    @Query(
        "DELETE FROM chat_table"
    )

    suspend fun clearChats()
}