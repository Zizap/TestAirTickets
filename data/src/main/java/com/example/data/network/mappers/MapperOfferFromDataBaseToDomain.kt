package com.example.data.network.mappers

import com.example.data.localDb.entities.OfferEntity
import com.example.data.localDb.entities.PriceEntity
import com.example.domain.models.offer.OfferDomain
import com.example.domain.models.offer.PriceDomain


object MapperOfferFromDataBaseToDomain {
    fun mapToDomainForData(offerEntity: OfferEntity): OfferDomain {
        return OfferDomain(
            id = offerEntity.id,
            title = offerEntity.title,
            town = offerEntity.town,
            price = mapPriceToDomain(offerEntity.price),
            image = offerEntity.image
        )
    }

    private fun mapPriceToDomain(priceEntity: PriceEntity): PriceDomain {
        return PriceDomain(
            value = priceEntity.value
        )
    }
}

fun OfferEntity.mapToDomain(): OfferDomain {
    return MapperOfferFromDataBaseToDomain.mapToDomainForData(this)
}