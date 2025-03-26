package com.example.mywordle.network.model

import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class OnlineWord(
    @SerialName(value = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerialName(value = "user_id")
    val userId: Int = 0,
    @SerialName(value = "word")
    val hiddenWord: String,
    @SerialName(value = "created_at")
    val createdAt: Instant,
    @SerialName(value = "updated_at")
    val updatedAt: Instant
)