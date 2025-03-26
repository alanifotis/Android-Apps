package com.example.mywordle.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mywordle.model.Word
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to read/write operations on the schedule table.
 * Used by the view models to format the query results for use in the UI.
 */
@Dao
interface WordDao {
    @Query(
        """
        SELECT * FROM word 
        ORDER BY id ASC    
        """
    )
    fun getAllWords(): Flow<List<Word>>

    @Query(
        """
        SELECT * FROM word 
        WHERE LENGTH(hidden_word) = :desiredWordLength
        ORDER BY RANDOM()
        LIMIT 1
        """
    )
    fun getRandomHiddenWord(desiredWordLength: Int): Flow<Word>

    @Query("SELECT * from word WHERE id = :id")
    fun getWordStream(id: Int): Flow<Word>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)
}