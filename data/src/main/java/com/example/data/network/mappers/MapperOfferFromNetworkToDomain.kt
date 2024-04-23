package com.example.data.network.mappers

import com.example.data.network.models.offer.OfferModel
import com.example.data.network.models.offer.PriceModel
import com.example.domain.models.offer.OfferDomain
import com.example.domain.models.offer.PriceDomain

object MapperOfferFromNetworkToDomain {
    fun mapToDomainForNetwork(offerModel: OfferModel): OfferDomain {
        return OfferDomain(
            id = offerModel.id,
            title = offerModel.title,
            town = offerModel.town,
            price = mapPriceToDomain(offerModel.price),
            image = null
        )
    }

    private fun mapPriceToDomain(priceModel: PriceModel): PriceDomain {
        return PriceDomain(
            value = priceModel.value
        )
    }
}

fun OfferModel.mapToDomain(): OfferDomain {
    return MapperOfferFromNetworkToDomain.mapToDomainForNetwork(this)
}