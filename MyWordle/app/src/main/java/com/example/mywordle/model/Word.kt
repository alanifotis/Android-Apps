package com.example.mywordle.model

import androidx.room.ColumnInfo
import androidx.room.Entity
// import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Represents a single table in the database. Each row is a separate instance of
 * the Word class. Each property corresponds to a column.
 * Additionally, an ID is needed as a unique identifier for
 * each row in the database.
 */

@Entity(tableName = "word")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /**
     * TODO: Create User Model -> Declare it as foreign key
     * -> Declare relationship with word one user many words
     * -> Declare relationship word belongs to one user or to gm
     * @ColumnInfo(name = "user_id")
     * @ForeignKey(tableName = "user_id")
     * val userId: Int
    */


    @ColumnInfo(name = "hidden_word")
    val hiddenWord: String
)