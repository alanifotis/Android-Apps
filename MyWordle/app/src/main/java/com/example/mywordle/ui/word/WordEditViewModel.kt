package com.example.mywordle.ui.word

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywordle.data.WordsRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


/**
 * ViewModel to retrieve and update an word from the [WordsRepository]'s data source.
 */
class WordEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val wordsRepository: WordsRepository
) : ViewModel() {

    /**
     * Holds current word ui state
     */
    var wordUiState by mutableStateOf(WordUiState())
        private set

    private val wordId: Int = checkNotNull(savedStateHandle[WordEditDestination.WORD_ID])

    init {
        viewModelScope.launch {
            wordUiState = wordsRepository.getWordStream(wordId)
                .filterNotNull()
                .first()
                .toWordUiState(true)
        }
    }

    /**
     * Update the word in the [WordsRepository]'s data source
     */
    suspend fun updateWord() {
        if (validateInput(wordUiState.wordDetails)) {
            wordsRepository.updateWord(wordUiState.wordDetails.toWord())
        }
    }

    /**
     * Updates the [wordUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(wordDetails: WordDetails) {
        wordUiState =
            WordUiState(wordDetails = wordDetails, isEntryValid = validateInput(wordDetails))
    }

    private fun validateInput(uiState: WordDetails = wordUiState.wordDetails): Boolean {
        return with(uiState) {
            wordToBeInserted.isNotBlank()
        }
    }
}
