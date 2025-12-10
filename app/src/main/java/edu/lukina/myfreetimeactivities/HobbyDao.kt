package edu.lukina.myfreetimeactivities

/**
Created by Yelyzaveta Lukina on 11.15.2025.
 */

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HobbyDao {

    @Query("SELECT * FROM hobbies ORDER BY id ASC")
    fun getAllHobbies(): LiveData<List<Hobby>>

    @Query("SELECT * FROM hobbies WHERE id = :id LIMIT 1")
    fun getHobbyById(id: Int): LiveData<Hobby>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hobbies: List<Hobby>)
}