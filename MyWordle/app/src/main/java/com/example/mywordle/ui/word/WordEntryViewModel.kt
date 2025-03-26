package com.example.mywordle.ui.word

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mywordle.model.Word
import com.example.mywordle.data.WordsRepository
/**
 * ViewModel to validate and insert words in the Room database.
 */
class WordEntryViewModel(private val wordsRepository: WordsRepository) : ViewModel() {

    /**
     * Holds current word ui state
     */
    var wordUiState by mutableStateOf(WordUiState())
        private set

    /**
     * Updates the [wordUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(wordDetails: WordDetails) {
        wordUiState =
            WordUiState(wordDetails = wordDetails, isEntryValid = validateInput(wordDetails))
    }

    suspend fun saveWord() {
        if (validateInput()) {
            wordsRepository.insertWord(wordUiState.wordDetails.toWord())
        }
    }

    private fun validateInput(uiState: WordDetails = wordUiState.wordDetails): Boolean {
        return with(uiState) {
            wordToBeInserted.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Word.
 */

data class WordUiState(
    val wordDetails: WordDetails = WordDetails(),
    val isEntryValid: Boolean = false
)

data class WordDetails(
    val id: Int = 0,
    val wordToBeInserted: String = "",
)

/**
 * Extension function to convert [WordDetails] to [Word]. If the value of [WordDetails.wordToBeInserted] is
 * a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [WordDetails] is not a valid [Int], then the quantity will be set to 0
 */

fun WordDetails.toWord(): Word = Word(
    id = id,
    hiddenWord = wordToBeInserted
)

/**
 * Extension function to convert [Word] to [WordUiState]
 */
fun Word.toWordUiState(isEntryValid: Boolean = false): WordUiState = WordUiState(
    wordDetails = this.toWordDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Word] to [WordDetails]
 */
fun Word.toWordDetails(): WordDetails = WordDetails(
    id = id,
    wordToBeInserted = hiddenWord
)
