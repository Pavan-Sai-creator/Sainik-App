package com.example.sainik

import androidx.lifecycle.LiveData
import com.example.sainik.fragments.EventsFragment
import com.example.sainik.fragments.EventsFragmentArgs

class Repository(private val userDao: UserDao, private val eventDao: EventDao): EventsFragment() {



    val getAllData: LiveData<List<UserData>> = userDao.getAllData()

    val getAllEventData: LiveData<List<EventData>> = eventDao.getAllData()



    suspend fun insertData(userData: UserData){
        userDao.insertData(userData)
    }

    suspend fun insertEventData(eventData: EventData){
        eventDao.insertData(eventData)
    }

    fun getMyEventsData(currUserNumber: String) : LiveData<List<EventData>>{
        return eventDao.getMyEventsData(currUserNumber)
    }

}