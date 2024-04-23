package com.example.domain.models.offer


data class OfferDomain(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceDomain,
    var image: Int?
)