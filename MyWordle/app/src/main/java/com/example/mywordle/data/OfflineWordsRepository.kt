package com.example.mywordle.data

import com.example.mywordle.model.Word
import kotlinx.coroutines.flow.Flow

class OfflineWordsRepository(private val wordDao: WordDao) : WordsRepository {

    override fun getAllWords(): Flow<List<Word>> = wordDao.getAllWords()

    override fun getWordStream(id: Int): Flow<Word?> = wordDao.getWordStream(id)

    override fun getRandomWord(desiredWordLength: Int)  = wordDao.getRandomHiddenWord(desiredWordLength)

    override suspend fun insertWord(word: Word) = wordDao.insert(word)

    override suspend fun deleteWord(word: Word) = wordDao.delete(word)

    override suspend fun updateWord(word: Word)  = wordDao.update(word)
}