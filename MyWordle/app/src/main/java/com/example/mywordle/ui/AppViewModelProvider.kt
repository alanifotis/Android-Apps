package com.example.mywordle.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mywordle.ui.home.HomeViewModel
import com.example.mywordle.ui.word.WordEntryViewModel
import com.example.mywordle.ui.word.WordEditViewModel

import com.example.mywordle.WordApplication
import com.example.mywordle.ui.online.ui.MultiplayerMyWordleView
import com.example.mywordle.ui.word.WordDetailsViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for WordEditViewModel
        initializer {
            WordEditViewModel(
                this.createSavedStateHandle(),
                myWordleApplication().container.wordsRepository
            )
        }
        // Initializer for WordEntryViewModel
        initializer {
            WordEntryViewModel(myWordleApplication().container.wordsRepository)
        }

        // Initializer for WordDetailsViewModel
        initializer {
            WordDetailsViewModel(
                this.createSavedStateHandle(),
                myWordleApplication().container.wordsRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(myWordleApplication().container.wordsRepository)
        }

        // Initializer for Multiplayer
        initializer {
            MultiplayerMyWordleView(myWordleApplication().container.onlineWordleRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [WordApplication].
 */
fun CreationExtras.myWordleApplication(): WordApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as WordApplication)
