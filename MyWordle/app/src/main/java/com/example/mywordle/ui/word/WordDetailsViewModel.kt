/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mywordle.ui.word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywordle.data.WordsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve, update and delete an word from the [WordsRepository]'s data source.
 */
class WordDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val wordsRepository: WordsRepository,
) : ViewModel() {

    private val wordId: Int = checkNotNull(savedStateHandle[WordDetailsDestination.WORD_ID])

    /**
     * Holds the word details ui state. The data is retrieved from [WordsRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<WordDetailsUiState> =
        wordsRepository.getWordStream(wordId)
            .filterNotNull()
            .map {
                WordDetailsUiState(wordDetails = it.toWordDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = WordDetailsUiState()
            )

    /**
     * Reduces the word quantity by one and update the [WordsRepository]'s data source.

        fun reduceQuantityByOne() {
            viewModelScope.launch {
                val currentWord = uiState.value.wordDetails.toWord()
                if (currentWord.quantity > 0) {
                    wordsRepository.updateWord(currentWord.copy(quantity = currentWord.quantity - 1))
                }
            }
        }
     */

    /**
     * Deletes the word from the [WordsRepository]'s data source.
     */
    suspend fun deleteWord() {
        wordsRepository.deleteWord(uiState.value.wordDetails.toWord())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for WordDetailsScreen
 */
data class WordDetailsUiState(
    val wordDetails: WordDetails = WordDetails()
)
