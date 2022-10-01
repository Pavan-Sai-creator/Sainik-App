package com.example.sainik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel(application: Application): AndroidViewModel(application) {
    private val eventDao = MyDatabase.getDatabase(application).eventDao()
    private val userDao = MyDatabase.getDatabase(application).userDao()
    private val repository: Repository

    val getAllEventData: LiveData<List<EventData>>

    init {
        repository = Repository(userDao,eventDao)
        getAllEventData = repository.getAllEventData
    }

    fun insertData(eventData: EventData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEventData(eventData)
        }

    }
}