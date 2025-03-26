package com.example.mywordle.data

import com.example.mywordle.model.Word
import kotlinx.coroutines.flow.Flow


/**
 * Repository that provides insert, update, delete, and retrieve of [Word] from a given data source.
 */
interface WordsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllWords(): Flow<List<Word>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getWordStream(id: Int): Flow<Word?>

    /**
     * Retrieve a random word that has user selected length [desiredWordLength].
     */

    fun getRandomWord(desiredWordLength: Int): Flow<Word>

    /**
     * Insert item in the data source
     */
    suspend fun insertWord(word: Word)

    /**
     * Delete item from the data source
     */
    suspend fun deleteWord(word: Word)

    /**
     * Update item in the data source
     */
    suspend fun updateWord(word: Word)
}