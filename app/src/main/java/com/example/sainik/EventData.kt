package com.example.sainik

import android.telephony.TelephonyCallback
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_details")
data class EventData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var event_title: String,
    var event_location: String,
    var event_capacity: String,
    var event_description: String,
    var organiser_name: String,
    var organiser_number: String
)
