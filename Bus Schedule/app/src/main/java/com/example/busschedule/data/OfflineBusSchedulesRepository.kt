package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusSchedulesRepository(private val busScheduleDao: BusScheduleDao) : SchedulesRepository {
    override fun getAllItemsStream(): Flow<List<BusSchedule>> = busScheduleDao.getAllSchedules()

    override fun getItemStream(id: Int): Flow<BusSchedule?> = busScheduleDao.getSchedule(id)

    override suspend fun insertItem(busSchedule: BusSchedule) = busScheduleDao.insert(busSchedule)

    override suspend fun deleteItem(busSchedule: BusSchedule) = busScheduleDao.delete(busSchedule)

    override suspend fun updateItem(busSchedule: BusSchedule) = busScheduleDao.update(busSchedule)

}