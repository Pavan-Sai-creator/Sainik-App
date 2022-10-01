package com.example.sainik

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserData::class, EventData::class], version = 7, exportSchema = false)
abstract class MyDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao

    // companion object is like public static final class
    companion object{

        @Volatile // Writes to this files are immediately made visible to other threads //
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            // when a thread calls synchronized, it acquires the lock of that synchronized block. Other threads
            // don't have permission to call same synchronized block as long as previous thread which had acquired
            // the lock does not release the lock
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "todo_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}