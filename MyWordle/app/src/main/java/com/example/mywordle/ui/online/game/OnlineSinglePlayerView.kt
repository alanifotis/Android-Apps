package com.example.mywordle.ui.online.game

import android.util.Log
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
import com.example.mywordle.network.deserializers.SingleWordDeserializer
import com.example.mywordle.network.model.OnlineWord
import com.example.mywordle.ui.word.WordDetails
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class OnlineSinglePlayerView(private var onlineWordleRepository: OnlineWordleRepository) : ViewModel() {

    var onlineSinglePlayerUiState = MutableStateFlow(WordDetails())
        private set

    init {
        viewModelScope.launch {
            getRandomWord()
        }
    }


    private suspend fun getRandomWord(): WordDetails {
        val baseUrl = "http://95.88.55.136:42069/api/"

        val onlineWordDeserializerGson = GsonBuilder()
            .registerTypeAdapter(OnlineWord::class.java as Type, SingleWordDeserializer())
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(onlineWordDeserializerGson))
            .build()

        val retrofitService: WordleApiService = retrofit.create(WordleApiService::class.java)

        onlineWordleRepository = NetworkMyWordleRepository(retrofitService)

        val mediaType = "application/json".toMediaType()
        val body = "{\r\n    \"length\": 4\r\n}".toRequestBody(mediaType)

        var response: OnlineWord? = null // Initialize response as null
        var attempts = 0 // Keep track of attempts
        val maxAttempts = 3 // Set a maximum number of attempts

        while (response == null && attempts < maxAttempts) { // Loop until a non-null response or max attempts reached
            try {
                response = onlineWordleRepository.getRandomWord(body)
                Log.d("OnlineSinglePlayerView", "Fetched random word: $response") // Log the successful response
            } catch (e: Exception) {
                Log.e("OnlineSinglePlayerView", "Error fetching random word (attempt ${attempts + 1}): ${e.message}")
                attempts++ // Increment attempts on error
            }
        }

        // Update the UI state only once after the loop
        onlineSinglePlayerUiState.value = response?.toWordDetails() ?: WordDetails(0, "")

        return onlineSinglePlayerUiState.value // Return the updated UI state

    }




    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WordApplication)
                val onlineWordsRepository = application.container.onlineWordleRepository
                OnlineSinglePlayerView(onlineWordleRepository = onlineWordsRepository)
            }
        }
    }
}

// Assuming 'OnlineWord' has properties 'id' and 'hiddenWord'
fun OnlineWord.toWordDetails(): WordDetails =  WordDetails(
        id = id, // Use 'this' to refer to the 'OnlineWord' instance
        wordToBeInserted = hiddenWord
)
