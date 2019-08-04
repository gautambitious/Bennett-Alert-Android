package com.gautam.bennettalert

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlertDao{
    @Insert
    fun insertRow(alert: Alert)

    @Query("select * from Alert")
    fun getAllAlerts(): LiveData<List<Alert>>
    
}