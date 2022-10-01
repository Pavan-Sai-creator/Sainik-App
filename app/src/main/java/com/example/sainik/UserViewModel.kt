package com.example.sainik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val userDao = MyDatabase.getDatabase(application).userDao()
    private val repository: Repository

    val getAllData: LiveData<List<UserData>>

    init {
        repository = Repository(userDao)
        getAllData = repository.getAllData
    }

    fun insertData(toDoData: UserData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }

    }
}