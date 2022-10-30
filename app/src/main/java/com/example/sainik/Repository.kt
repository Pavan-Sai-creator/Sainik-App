package com.example.sainik

import androidx.lifecycle.LiveData
import com.example.sainik.fragments.EventsFragment
import com.example.sainik.fragments.EventsFragmentArgs

class Repository(private val userDao: UserDao, private val eventDao: EventDao): EventsFragment() {



    val getAllData: LiveData<List<UserData>> = userDao.getAllData()

    val getAllEventData: LiveData<List<EventData>> = eventDao.getAllData()

    //val getCurrentEventData: LiveData<EventData> = eventDao.getCurrentEventData()



    suspend fun insertData(userData: UserData){
        userDao.insertData(userData)
    }

    suspend fun insertEventData(eventData: EventData){
        eventDao.insertData(eventData)
    }

    fun getMyEventsData(currUserNumber: String) : LiveData<List<EventData>>{
        return eventDao.getMyEventsData(currUserNumber)
    }

    suspend fun getCurrentEventData(currUserNumber: String,currEventTitle:String,currEventLocation:String): EventData{
        return eventDao.getCurrentEventData(currUserNumber,currEventTitle,currEventLocation)
    }

    suspend fun cancelEvent(currUserNumber: String,currEventTitle:String,currEventLocation:String){
        eventDao.cancelEvent(currUserNumber,currEventTitle,currEventLocation)
    }

    suspend fun updateData(currUserNumber: String,currEventTitle:String,currEventLocation:String){
        eventDao.updateData(currUserNumber,currEventTitle,currEventLocation)
    }



}