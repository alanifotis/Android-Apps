package com.example.mywordle.network.deserializers

import android.util.Log
import com.example.mywordle.network.model.OnlineWord
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import kotlinx.datetime.Instant
import java.lang.reflect.Type

class OnlineWordDeserializer : JsonDeserializer<List<OnlineWord>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): List<OnlineWord> {
        try {
            val dataArray = json.asJsonObject.getAsJsonArray("data")

            val onlineWords = mutableListOf<OnlineWord>()

            for (element in dataArray) {
                val jsonObject = element.asJsonObject
                val attributes = jsonObject.getAsJsonObject("attributes")

                val id = jsonObject.get("id").asInt
                val word = attributes.get("word").asString
                val createdAt = Instant.parse(attributes.get("createdAt").asString)
                val updatedAt = Instant.parse(attributes.get("updatedAt").asString)

                onlineWords.add(OnlineWord(id, userId = 0, word, createdAt, updatedAt))
            }

            return onlineWords

        } catch (e: IllegalStateException) {
            Log.e("deserializer", e.toString())
            return emptyList()
        }
    }
}