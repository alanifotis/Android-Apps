package com.example.busschedule.data

import android.content.Context

interface AppContainer {
    val schedulesRepository: SchedulesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val schedulesRepository: SchedulesRepository by lazy {
        OfflineBusSchedulesRepository(SchedulesDatabase.getDatabase(context).busScheduleDao())
    }
}