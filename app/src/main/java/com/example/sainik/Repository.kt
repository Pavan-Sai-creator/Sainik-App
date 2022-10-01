package com.example.sainik

import androidx.lifecycle.LiveData

class Repository(private val userDao: UserDao) {
    val getAllData: LiveData<List<UserData>> = userDao.getAllData()

    suspend fun insertData(userData: UserData){
        userDao.insertData(userData)
    }

}