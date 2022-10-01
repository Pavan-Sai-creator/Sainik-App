package com.example.sainik

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user_details")
    fun getAllData(): LiveData<List<UserData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(userData: UserData)
}