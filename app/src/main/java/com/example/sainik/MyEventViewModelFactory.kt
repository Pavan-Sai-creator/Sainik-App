package com.example.sainik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyEventViewModelFactory(application: Application,private val currUserNumber: String): ViewModelProvider.Factory, AndroidViewModel(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyEventViewModel(getApplication(),currUserNumber) as T
    }
}