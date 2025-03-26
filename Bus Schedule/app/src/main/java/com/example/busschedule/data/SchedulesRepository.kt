package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface SchedulesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<BusSchedule>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<BusSchedule?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(busSchedule: BusSchedule)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(busSchedule: BusSchedule)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(busSchedule: BusSchedule)
}