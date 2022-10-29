package com.example.sainik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Appendable

class UserViewModelFactory(application: Application): ViewModelProvider.Factory, AndroidViewModel(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(getApplication()) as T
    }
}