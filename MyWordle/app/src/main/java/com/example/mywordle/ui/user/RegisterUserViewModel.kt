package com.example.mywordle.ui.user

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
import com.example.mywordle.network.model.User
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * UI state for the Multiplayer Screen
 */
sealed interface RegisterUserUiState {
    data object Success: RegisterUserUiState
    data object Error : RegisterUserUiState
    data object Loading : RegisterUserUiState
}

class RegisterUserViewModel(private var onlineUserRepository: OnlineWordleRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var registerUserUiState: RegisterUserUiState by mutableStateOf(RegisterUserUiState.Loading)
        private set

    var userUiState by mutableStateOf(UserUiState())
        private set


    /**
     * Registers a new user in the Database using MyWordle API Retrofit service
     */

    private suspend fun registerUserRequest(user: User) {
        viewModelScope.launch {
            registerUserUiState = RegisterUserUiState.Loading
            registerUserUiState = try {

                val baseUrl = "http://95.88.55.136:42069/api/"

                val retrofit2 = Retrofit.Builder()
                    .baseUrl(baseUrl) // Replace with your base URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val retrofitService: WordleApiService by lazy {
                    retrofit2.create(WordleApiService::class.java)
                }

                onlineUserRepository = NetworkMyWordleRepository(retrofitService)



                val mediaType = "application/json".toMediaType()
                val body = "{\r\n    \"data\": {\r\n        \"attributes\": {\r\n            \"name\" : \"${user.name}\",\r\n            \"email\" : \"${user.email}\",\r\n            \"is_game_master\": false,\r\n            \"password\" : \"${user.password}\"\r\n        }\r\n    }\r\n}".toRequestBody(mediaType)

                val registeredUser = onlineUserRepository.registerUser(body)

                if (registeredUser != null) {
                    RegisterUserUiState.Success
                } else {
                    throw IOException("Something went wrong")
                }
            } catch (e: IOException) {
                Log.e("user", e.toString())
                RegisterUserUiState.Error
            } catch (e: HttpException) {
                Log.e("user", e.toString())
                RegisterUserUiState.Error
            }
        }
    }

    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }

    suspend fun saveUser() {
        if (validateInput()) {
            registerUserRequest(userUiState.userDetails.toUser())
        }
    }

    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            userNameToBeInserted.isNotBlank() &&
            userPasswordToBeInserted.isNotBlank() &&
            userPasswordToBeInserted.length > 4 &&
            userEmailToBeInserted.isNotBlank()
        }
    }

    /**
     * Factory for [RegisterUserViewModel] that takes [OnlineWordleRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WordApplication)
                val onlineUserRepository = application.container.onlineWordleRepository
                RegisterUserViewModel(onlineUserRepository = onlineUserRepository)
            }
        }
    }
}

/**
 * Represents Ui State for an User.
 */

data class UserUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)

data class UserDetails(
    val id: Int = 0,
    val userNameToBeInserted: String = "",
    val userEmailToBeInserted: String = "",
    val userPasswordToBeInserted: String = "",
)

/**
 * Extension function to convert [UserDetails] to [User]. If the value of [UserDetails] is
 * a valid [User], then the price will be set to 0.0. Similarly if the value of
 * [UserDetails] is not a valid [Int], then the quantity will be set to 0
 */

fun UserDetails.toUser(): User = User(
    id = id,
    name = userNameToBeInserted,
    password = userPasswordToBeInserted,
    email = userEmailToBeInserted,
    isGameMaster = false
)

/**
 * Extension function to convert [User] to [UserUiState]
 */
fun User.toUserUiState(isEntryValid: Boolean = false): UserUiState = UserUiState(
    userDetails = this.toUserDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [User] to [UserDetails]
 */
fun User.toUserDetails(): UserDetails = UserDetails(
    id = id,
    userNameToBeInserted = name,
    userPasswordToBeInserted = password,
    userEmailToBeInserted = password
)
