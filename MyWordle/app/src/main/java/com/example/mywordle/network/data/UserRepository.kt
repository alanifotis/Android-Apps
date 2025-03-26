package com.example.mywordle.network.data

import com.example.mywordle.network.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    /**
     * Retrieve all the Users from the the given data source.
     */
    fun getAllUsers(): Flow<List<User>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getUser(id: Int): Flow<User?>

    /**
     * Insert item in the data source
     */
    suspend fun registerUser(user: User)

    /**
     * Delete item from the data source
     */
    suspend fun deleteUser(user: User)

    /**
     * Update item in the data source
     */
    suspend fun updateUser(user: User)
}