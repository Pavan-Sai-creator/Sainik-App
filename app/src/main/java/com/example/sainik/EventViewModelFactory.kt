package com.example.sainik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EventViewModelFactory(application: Application): ViewModelProvider.Factory, AndroidViewModel(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventViewModel(getApplication()) as T
    }

}