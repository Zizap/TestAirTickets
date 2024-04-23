package com.example.domain.usecase

import com.example.domain.models.Either
import com.example.domain.models.offer.OfferDomain
import com.example.domain.repositories.OfferRepository

class OfferUseCase(
    private val offerRepository: OfferRepository
) {
    suspend fun loadOffer(): Either<List<OfferDomain>, Exception>{
        return offerRepository.loadOffer()
    }

    suspend fun loadOfferFromDB(): List<OfferDomain> {
        return offerRepository.loadOfferFromDB()
    }
}