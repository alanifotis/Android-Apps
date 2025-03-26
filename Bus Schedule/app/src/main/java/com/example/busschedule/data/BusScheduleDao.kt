package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao  {
    @Query("select * from schedule order by arrival_time asc")
    fun getAllSchedules(): Flow<List<BusSchedule>>

    @Query("select * from schedule where id = :id")
    fun getSchedule(id: Int): Flow<BusSchedule>

    @Insert
    suspend fun insert(busSchedule: BusSchedule)

    @Update
    suspend fun update(busSchedule: BusSchedule)

    @Delete
    suspend fun delete(busSchedule: BusSchedule)

    @Query("select * from schedule where stop_name = :stopName")
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>
}