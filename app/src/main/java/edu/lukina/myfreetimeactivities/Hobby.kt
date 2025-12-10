package edu.lukina.myfreetimeactivities

/**
 * Created by Yelyzaveta Lukina on 11.15.2025.
 */

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hobbies")
data class Hobby(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,    // Room will assign ID

    val name: String,
    val description: String
)

