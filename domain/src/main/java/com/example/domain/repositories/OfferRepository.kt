package com.example.domain.repositories

import com.example.domain.models.Either
import com.example.domain.models.offer.OfferDomain

interface OfferRepository {

    suspend fun loadOffer(): Either<List<OfferDomain>, Exception>

    suspend fun loadOfferFromDB(): List<OfferDomain>

}