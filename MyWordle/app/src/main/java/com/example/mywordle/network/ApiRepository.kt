package com.example.mywordle.network

import com.example.mywordle.network.deserializers.OnlineWordDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

interface ApiRepository {
    var BASE_URL: String
    var token: String?
    var retrofit: Retrofit
    var gsonConverterFactory: Gson
    var client: OkHttpClient
}

enum class RequestType {
    GET,
    PUT,
    POST,
    DELETE
}


class ApiConnector(requestType: RequestType) : ApiRepository {

    override var BASE_URL: String
    override var token: String? = null
    override var retrofit: Retrofit
    override var gsonConverterFactory: Gson
    override var client: OkHttpClient


    init {

        when (requestType) {
            RequestType.GET -> {
            BASE_URL = "myapi"
            token = "token"
            client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    chain.proceed(request)
                }.build()

            gsonConverterFactory = GsonBuilder()
                .registerTypeAdapter(List::class.java as Type, OnlineWordDeserializer())
                .create()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonConverterFactory))
                .build()
        }
            RequestType.PUT -> {
                BASE_URL = "myapi"
                token = "token"
                client = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Authorization", "Bearer $token")
                            .build()
                        chain.proceed(request)
                    }.build()

                gsonConverterFactory = GsonBuilder()
                    .registerTypeAdapter(List::class.java as Type, OnlineWordDeserializer())
                    .create()

                retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonConverterFactory))
                    .build()
            }
            RequestType.POST -> {
                BASE_URL = "myapi"
                token = "token"
                client = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Authorization", "Bearer $token")
                            .build()
                        chain.proceed(request)
                    }.build()

                gsonConverterFactory = GsonBuilder()
                    .registerTypeAdapter(List::class.java as Type, OnlineWordDeserializer())
                    .create()

                retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonConverterFactory))
                    .build()
            }
            RequestType.DELETE ->  {
                BASE_URL = "myapi"
                token = "token"
                client = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Authorization", "Bearer $token")
                            .build()
                        chain.proceed(request)
                    }.build()

                gsonConverterFactory = GsonBuilder()
                    .registerTypeAdapter(List::class.java as Type, OnlineWordDeserializer())
                    .create()

                retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonConverterFactory))
                    .build()
            }
        }
    }
}