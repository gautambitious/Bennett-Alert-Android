package com.gautam.bennettalert

import androidx.room.*

@Entity
data class Alert(
    @PrimaryKey(autoGenerate = true)
    val queryId: Long,
    val queryDetails: String,
    val queryAddress: String,
    val queryPriorityHigh: Boolean
)

