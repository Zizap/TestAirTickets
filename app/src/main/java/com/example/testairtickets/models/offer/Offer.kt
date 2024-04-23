package com.example.testairtickets.models.offer


data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val offerPrice: OfferPrice,
    var image: Int?
)