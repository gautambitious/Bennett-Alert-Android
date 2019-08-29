package com.gautam.bennettalert

import androidx.room.*
import java.util.*

@Entity
data class Alert(
    @PrimaryKey(autoGenerate = true)
    val queryId: Int,
    val userId: String,
    val date: String,

    val queryDetails: String,
    val queryAddress: String,
    val queryPriorityHigh: Boolean
)