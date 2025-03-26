package com.android.flightsearch.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "airports")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "iata_code")
    val iataCode: String,
    @ColumnInfo(name = "name")
    val airportName: String,
    @ColumnInfo(name = "passengers")
    val passenger: Int
)

@Fts4(contentEntity = Airport::class)
@Entity(tableName = "airportsFts")
data class AirportFts(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "iata_code")
    val iataCode: String,
    @ColumnInfo(name = "name")
    val airportName: String,
    @ColumnInfo(name = "passengers")
    val passenger: Int
)