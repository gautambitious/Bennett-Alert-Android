package com.gautam.bennettalert

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Alert::class],version = 1)
abstract class AlertDatabase : RoomDatabase() {
    abstract fun alertDao():AlertDao
    companion object{
        fun createDatabase(context: Context):AlertDatabase
                = Room.databaseBuilder(context,AlertDatabase::class.java, "alerts.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}

