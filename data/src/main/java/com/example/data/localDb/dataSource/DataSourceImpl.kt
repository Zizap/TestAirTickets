package com.example.data.localDb.dataSource

import com.example.data.localDb.dao.OfferDao
import com.example.data.localDb.entities.OfferEntity
import com.example.data.localDb.entities.PriceEntity

class DataSourceImpl(
    private val offerDao: OfferDao
) : DataSource {

    override suspend fun insertOffer(items: List<OfferEntity>) {
        offerDao.insertOffer(items)
    }

    override suspend fun insertOfferPrice(item: PriceEntity) {
        offerDao.insertOfferPrice(item)
    }

    override suspend fun clear() {
        offerDao.clear()
    }

    override fun getAllOffers(): List<OfferEntity> {
        return offerDao.getAllOffers()
    }
}