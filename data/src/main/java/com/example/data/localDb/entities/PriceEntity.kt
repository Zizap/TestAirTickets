package com.example.data.localDb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class PriceEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "price_id")
    val id: Int,
    @ColumnInfo(name = "value")
    val value: Int
)