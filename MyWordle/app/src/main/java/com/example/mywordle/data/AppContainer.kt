package com.example.mywordle.data

import android.content.Context
import com.example.mywordle.network.api.WordleApiService
import com.example.mywordle.network.data.NetworkMyWordleRepository
import com.example.mywordle.network.data.OnlineWordleRepository
import com.example.mywordle.network.deserializers.OnlineWordDeserializer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


/**
 * App container for Dependency injection.
 */

interface AppContainer {
    val wordsRepository: WordsRepository
    val onlineWordleRepository: OnlineWordleRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineWordsRepository]
 */

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [OfflineWordsRepository] (Local Database)
     */
    override val wordsRepository: WordsRepository by lazy {
        OfflineWordsRepository(AppDatabase.getDatabase(context).wordDao())
    }

    private val baseUrl = "http://95.88.55.136:42069/api/"
    @Suppress("SpellCheckingInspection")
    private val token = "3AlfLgi6Se5g4UFB383llrJfGWz9k3DFOiCgfanV0da2b1ea"

    private val onlineWordDeserializerGson = GsonBuilder()
        .registerTypeAdapter(List::class.java as Type, OnlineWordDeserializer())
        .create()


    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }.build()

    private val retrofit2 = Retrofit.Builder()
        .baseUrl(baseUrl) // Replace with your base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Use the Retrofit builder to build a retrofit object using GsonConverterFactory

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(onlineWordDeserializerGson))
        .build()
    */

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: WordleApiService by lazy {
        retrofit2.create(WordleApiService::class.java)
    }

    /**
     * DI implementation for Words repository
     */
    override val onlineWordleRepository: OnlineWordleRepository by lazy {
        NetworkMyWordleRepository(retrofitService)
    }
}
