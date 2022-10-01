package com.example.sainik

import androidx.lifecycle.LiveData

class Repository(private val userDao: UserDao, private val eventDao: EventDao) {
    val getAllData: LiveData<List<UserData>> = userDao.getAllData()

    val getAllEventData: LiveData<List<EventData>> = eventDao.getAllData()

    suspend fun insertData(userData: UserData){
        userDao.insertData(userData)
    }

    suspend fun insertEventData(eventData: EventData){
        eventDao.insertData(eventData)
    }

}