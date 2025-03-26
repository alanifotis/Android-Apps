package com.android.flightsearch.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.android.flightsearch.data.models.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("""select * from airports 
            join airportsFts on airports.id == airportsFts.id 
            where airportsFts.name match :partialQuery"""
    )
    fun fuzzySearch(partialQuery: String): Flow<List<Airport>>
}