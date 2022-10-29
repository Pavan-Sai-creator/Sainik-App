package com.example.sainik

import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sainik.fragments.EventsFragment
import com.example.sainik.fragments.EventsFragmentArgs

@Dao
interface EventDao {
    @Query("SELECT * FROM event_details")
    fun getAllData(): LiveData<List<EventData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(eventData: EventData)

    @Query("SELECT * FROM event_details WHERE organiser_number = :curUserNo")
    fun getMyEventsData(curUserNo: String): LiveData<List<EventData>>

}