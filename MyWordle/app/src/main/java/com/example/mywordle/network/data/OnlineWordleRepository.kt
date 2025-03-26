package com.example.mywordle.network.data

import com.example.mywordle.model.Word
import com.example.mywordle.network.model.OnlineWord
import com.example.mywordle.network.api.WordleApiService
import com.example.mywordle.network.model.User
import okhttp3.RequestBody

/**
 * Repository that fetch words list from wordApi.
 */
interface OnlineWordleRepository {
    /** Fetches list of words from wordApi */
    suspend fun getAllWords(): List<OnlineWord>
    suspend fun registerUser(requestBody: RequestBody): User?
    suspend fun getRandomWord(requestBody: RequestBody): OnlineWord?
}

/**
 * Network Implementation of Repository that fetch words list from wordApi.
 */
class NetworkMyWordleRepository(
    private val wordleApiService: WordleApiService,
) : OnlineWordleRepository {
    /** Fetches list of words from WordApi */
    override suspend fun getAllWords(): List<OnlineWord> = wordleApiService.getAllWords()

    /** Register User Request through WordApi */
    override suspend fun registerUser(requestBody: RequestBody): User? = wordleApiService.registerUser(requestBody)

    override suspend fun getRandomWord(requestBody: RequestBody): OnlineWord? = wordleApiService.getRandomWord(requestBody)
}