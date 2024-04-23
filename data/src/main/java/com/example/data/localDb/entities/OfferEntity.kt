package com.example.data.localDb.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = PriceEntity::class,
            parentColumns = ["price_id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    tableName = "offer_data_table"
)
data class OfferEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "town")
    val town: String,
    @ColumnInfo(name = "image")
    val image: Int?

) {
    @Embedded
    lateinit var price: PriceEntity
}
