package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.SchedulesDatabase

class BusScheduleApplication: Application() {
    val database: SchedulesDatabase by lazy {
        SchedulesDatabase.getDatabase(this)
    }
}