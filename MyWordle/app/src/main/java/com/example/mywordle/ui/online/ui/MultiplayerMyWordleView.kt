package com.example.mywordle.ui.online.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mywordle.WordApplication
import com.example.mywordle.network.api.WordleApiService
import com.example.mywordle.network.data.NetworkMyWordleRepository
import com.example.mywordle.network.data.OnlineWordleRepository
import com.example.mywordle.network.deserializers.OnlineWordDeserializer
import com.example.mywordle.network.model.OnlineWord
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type

/**
 * UI state for the Multiplayer Screen
 */
sealed interface MultiplayerUiState {
    data class Success(val words: List<OnlineWord>) : MultiplayerUiState
    data object Error : MultiplayerUiState
    data object Loading : MultiplayerUiState
}

class MultiplayerMyWordleView(private var onlineWordleRepository: OnlineWordleRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var multiplayerUiState: MultiplayerUiState by mutableStateOf(MultiplayerUiState.Loading)
        private set

    /**
     * Call getAllWords() on init so we can display status immediately.
     */
    init {
        getAllWords()
    }

    /**
     * Gets Words from the MyWordle API Retrofit service and updates the
     * [OnlineWord] [List] [MutableList].
     */
    fun getAllWords() {
        viewModelScope.launch {
            multiplayerUiState = MultiplayerUiState.Loading
            multiplayerUiState = try {
                val baseUrl = "http://95.88.55.136:42069/api/"

                @Suppress("SpellCheckingInspection")
                val token = "s7DaIEiOFqfAE1DGRAsZz0ZEhtmyJEm70lRVasJq5735d7a5"



                val onlineWordDeserializerGson = GsonBuilder()
                    .registerTypeAdapter(List::class.java as Type, OnlineWordDeserializer())
                    .create()


                val client = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Authorization", "Bearer $token")
                            .build()
                        chain.proceed(request)
                    }.build()

                val retrofit: Retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(onlineWordDeserializerGson))
                    .build()

                val retrofitService: WordleApiService = retrofit.create(WordleApiService::class.java)

                onlineWordleRepository = NetworkMyWordleRepository(retrofitService)


                val onlineWords: List<OnlineWord> = onlineWordleRepository.getAllWords()

                MultiplayerUiState.Success(onlineWords /**
                        .map { onlineWord ->
                    Word(
                        onlineWord.id,
                        onlineWord.hiddenWord
                    )
                }*/)
            } catch (e: IOException) {
                Log.e("wordle", e.toString())
                MultiplayerUiState.Error
            } catch (e: HttpException) {
                Log.e("wordle", e.toString())
                MultiplayerUiState.Error
            }
        }
    }

    /**
     * Factory for [MultiplayerMyWordleView] that takes [OnlineWordleRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WordApplication)
                val onlineWordsRepository = application.container.onlineWordleRepository
                MultiplayerMyWordleView(onlineWordleRepository = onlineWordsRepository)
            }
        }
    }
}