package edu.lukina.myfreetimeactivities

/**
 * Created by Yelyzaveta Lukina on 11.15.2025.
 */

import android.content.Context
import androidx.lifecycle.LiveData

class HobbyRepository private constructor(context: Context) {

    private val hobbyDao = HobbyDatabase.getDatabase(context).hobbyDao()

    val allHobbies: LiveData<List<Hobby>> = hobbyDao.getAllHobbies()

    fun getHobbyById(id: Int): LiveData<Hobby> {
        return hobbyDao.getHobbyById(id)
    }

    companion object {
        @Volatile
        private var INSTANCE: HobbyRepository? = null

        fun getInstance(context: Context): HobbyRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = HobbyRepository(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }
}