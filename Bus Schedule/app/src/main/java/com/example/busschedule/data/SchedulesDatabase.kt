package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusSchedule::class], version = 1, exportSchema = true)
abstract class SchedulesDatabase : RoomDatabase() {

    abstract fun busScheduleDao(): BusScheduleDao

    companion object {
        @Volatile
        private var Instance: SchedulesDatabase? = null

        fun getDatabase(context: Context): SchedulesDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, SchedulesDatabase::class.java, "schedule_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
