package com.example.data.network.mappers

import com.example.data.localDb.entities.OfferEntity
import com.example.data.localDb.entities.PriceEntity
import com.example.domain.models.offer.OfferDomain
import com.example.domain.models.offer.PriceDomain


object MapperOfferFromDomainToDataBase {
    fun mapOfferToDataBase(offerDomain: OfferDomain): OfferEntity {
        val offerEntity =  OfferEntity(
            id = offerDomain.id,
            title = offerDomain.title,
            town = offerDomain.town,
            image = offerDomain.image
        )

        offerEntity.price = PriceEntity(
            id = offerDomain.id,
            value = offerDomain.price.value
        )

        return offerEntity
    }

}

fun OfferDomain.mapToDataBase(): OfferEntity {
    return MapperOfferFromDomainToDataBase.mapOfferToDataBase(this)
}