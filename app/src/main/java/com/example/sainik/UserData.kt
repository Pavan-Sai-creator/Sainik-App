package com.example.sainik

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_details")
data class UserData(
    @PrimaryKey
    var phoneNumber: String,
    var password: String,
)
