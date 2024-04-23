package com.example.testairtickets.mappers

import com.example.domain.models.offer.OfferDomain
import com.example.domain.models.offer.PriceDomain
import com.example.testairtickets.models.offer.Offer
import com.example.testairtickets.models.offer.OfferPrice


object MapperOfferFromDomainToApp {
    fun mapToAppForDomain(offerDomain: OfferDomain): Offer {
        return Offer(
            id = offerDomain.id,
            title = offerDomain.title,
            town = offerDomain.town,
            offerPrice = mapPriceToApp(offerDomain.price),
            image = offerDomain.image
        )
    }

    private fun mapPriceToApp(priceDomain: PriceDomain): OfferPrice {
        return OfferPrice(
            value = priceDomain.value
        )
    }
}

fun OfferDomain.mapToApp(): Offer {
    return MapperOfferFromDomainToApp.mapToAppForDomain(this)
}