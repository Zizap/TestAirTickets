package com.example.data.localDb


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.localDb.dao.OfferDao
import com.example.data.localDb.entities.OfferEntity
import com.example.data.localDb.entities.PriceEntity


@Database(
    entities = [
        OfferEntity::class,
        PriceEntity::class,
    ],
    version = 1
)
@TypeConverters()
abstract class DataBase : RoomDatabase() {
    abstract fun offerDao(): OfferDao
}