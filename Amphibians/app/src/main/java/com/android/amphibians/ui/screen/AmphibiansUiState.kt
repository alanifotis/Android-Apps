package com.android.amphibians.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.android.amphibians.AmphibiansPhotosApplication
import com.android.amphibians.data.AmphibiansPhotosRepository
import com.android.amphibians.network.Amphibians
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibians>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}

class AmphibiansViewModel(
    private val amphibiansPhotosRepository: AmphibiansPhotosRepository
) : ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set


    init {
        getAmphibiansPhotos()
    }

    fun getAmphibiansPhotos() {
        viewModelScope.launch {
            amphibiansUiState = try {
                AmphibiansUiState.Success(amphibiansPhotosRepository.getAmphibiansPhotos())
            } catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansPhotosApplication)
                val amphibiansPhotosRepository = application.container.amphibiansPhotosRepository
                AmphibiansViewModel(amphibiansPhotosRepository)
            }
        }
    }
}
