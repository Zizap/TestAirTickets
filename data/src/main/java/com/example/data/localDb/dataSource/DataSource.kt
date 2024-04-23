package com.example.data.localDb.dataSource

import com.example.data.localDb.entities.OfferEntity
import com.example.data.localDb.entities.PriceEntity

interface DataSource {

    suspend fun insertOffer(items: List<OfferEntity>)

    suspend fun insertOfferPrice(item: PriceEntity)

    suspend fun clear()

    fun getAllOffers(): List<OfferEntity>

}