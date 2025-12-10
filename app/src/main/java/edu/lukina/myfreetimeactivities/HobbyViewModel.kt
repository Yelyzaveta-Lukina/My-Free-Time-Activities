package edu.lukina.myfreetimeactivities

/**
 * Created by Yelyzaveta Lukina on 11.15.2025.
 */

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HobbyViewModel(private val repository: HobbyRepository) : ViewModel() {

    val allHobbies: LiveData<List<Hobby>> = repository.allHobbies

    fun getHobbyById(id: Int): LiveData<Hobby> {
        return repository.getHobbyById(id)
    }
}

class HobbyViewModelFactory(private val repository: HobbyRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HobbyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HobbyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}