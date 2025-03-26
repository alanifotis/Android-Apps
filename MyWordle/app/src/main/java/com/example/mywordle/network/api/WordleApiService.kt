package com.example.mywordle.network.api

import com.example.mywordle.network.model.OnlineWord
import com.example.mywordle.network.model.User
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * A public interface that exposes the [getAllWords] method
 */
interface WordleApiService {

    /**
     * Returns a [List] of [OnlineWord] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "words" endpoint will be requested with the GET
     * HTTP method
     */

    @GET("v1/words")
    suspend fun getAllWords(): List<OnlineWord>

    @POST("register")
    suspend fun registerUser(@Body requestBody: RequestBody): User?

    @POST("wordle")
    suspend fun getRandomWord(@Body requestBody: RequestBody): OnlineWord?
}
