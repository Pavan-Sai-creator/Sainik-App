package com.example.sainik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EventStatisticsFragmentViewModelFactory(application: Application,
private val currUserPhoneNumber: String,
private val currEventTitle: String,
private val currEventLocation: String): ViewModelProvider.Factory, AndroidViewModel(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventStatisticsFragmentViewModel(getApplication(),currUserPhoneNumber,currEventTitle,currEventLocation) as T
    }

}