package com.example.sainik

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {
    @Query("SELECT * FROM event_details")
    fun getAllData(): LiveData<List<EventData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(eventData: EventData)
}