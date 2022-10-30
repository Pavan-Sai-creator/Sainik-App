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

    @Query("SELECT * FROM event_details WHERE organiser_number = :curUserNo AND event_title = :currEventTitle AND event_location = :currEventLocation")
    suspend fun getCurrentEventData(curUserNo: String,currEventTitle:String,currEventLocation:String): EventData

    @Query("DELETE FROM event_details WHERE organiser_number = :curUserNo AND event_title = :currEventTitle AND event_location = :currEventLocation")
    suspend fun cancelEvent(curUserNo: String,currEventTitle:String,currEventLocation:String)

    @Query("UPDATE event_details SET number_of_participants = (number_of_participants+1) WHERE organiser_number = :curUserNo AND event_title = :currEventTitle AND event_location = :currEventLocation")
    suspend fun updateData(curUserNo: String,currEventTitle:String,currEventLocation:String)

}