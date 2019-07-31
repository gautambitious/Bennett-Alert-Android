package com.gautam.bennettalert

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AlertDao{
    @Insert
    fun insertRow(alert: Alert)
}