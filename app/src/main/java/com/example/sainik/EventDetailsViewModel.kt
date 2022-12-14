package com.example.sainik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventDetailsViewModel(application: Application,
                                       private val currUserNumber: String,
                                       private val eventTitle: String,
                                       private val eventLocation: String
): AndroidViewModel(application) {
    private val eventDao = MyDatabase.getDatabase(application).eventDao()
    private val userDao = MyDatabase.getDatabase(application).userDao()
    val repository: Repository

    lateinit var getCurrentEventData: EventData

    init {
        repository = Repository(userDao,eventDao)
        getCurrentData()
        //getCurrentEventData = repository.getCurrentEventData(currUserNumber,eventTitle,eventLocation)
    }

    // TO DO:

    fun getCurrentData(){
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentEventData = repository.getCurrentEventData(currUserNumber,eventTitle,eventLocation)
        }
    }


    fun updateData(currUserNumber: String,currEventTitle:String,currEventLocation:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(currUserNumber,currEventTitle,currEventLocation)
        }

    }

}