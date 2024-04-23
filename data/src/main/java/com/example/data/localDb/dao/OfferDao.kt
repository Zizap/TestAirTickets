package com.example.data.localDb.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.localDb.entities.OfferEntity
import com.example.data.localDb.entities.PriceEntity

@Dao
interface OfferDao {

    @Upsert
    suspend fun insertOffer(items: List<OfferEntity>)

    @Upsert
    suspend fun insertOfferPrice(item: PriceEntity)

    @Query("DELETE FROM offer_data_table")
    suspend fun clear()

    @Query("SELECT * FROM offer_data_table")
    fun getAllOffers(): List<OfferEntity>

}