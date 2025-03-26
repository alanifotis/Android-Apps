package com.example.mywordle.network.deserializers

import android.util.Log
import com.example.mywordle.network.model.OnlineWord
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.lang.reflect.Type

class SingleWordDeserializer : JsonDeserializer<OnlineWord> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): OnlineWord {
        try {

                val jsonObject = json.asJsonObject
                val id = jsonObject.get("id").asInt
                val userId = jsonObject.get("user_id").asInt
                val word = jsonObject.get("word").asString
                val createdAt = Instant.parse(jsonObject.get("created_at").asString)
                val updatedAt = Instant.parse(jsonObject.get("updated_at").asString)




            return OnlineWord(id, userId, word, createdAt, updatedAt)

        } catch (e: IllegalStateException) {
            Log.e("deserializer", e.toString())
            return OnlineWord(0,0,"",Instant.parse(Clock.System.now().toString()),Instant.parse(Clock.System.now().toString()))
        }
    }
}