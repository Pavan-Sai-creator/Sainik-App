package com.example.sainik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyEventViewModel(application: Application, currUserNumber: String): AndroidViewModel(application) {
    private val eventDao = MyDatabase.getDatabase(application).eventDao()
    private val userDao = MyDatabase.getDatabase(application).userDao()
    private val repository: Repository

    val getMyEventData: LiveData<List<EventData>>

    init {
        repository = Repository(userDao,eventDao)
        getMyEventData = repository.getMyEventsData(currUserNumber)
    }

    fun insertData(eventData: EventData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEventData(eventData)
        }

    }
}