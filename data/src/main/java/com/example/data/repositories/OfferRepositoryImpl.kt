package com.example.data.repositories

import com.example.data.R
import com.example.data.localDb.dataSource.DataSource
import com.example.data.localDb.entities.PriceEntity
import com.example.data.network.apiDataSource.ApiDataSource
import com.example.data.network.mappers.mapToDataBase
import com.example.data.network.mappers.mapToDomain
import com.example.domain.models.Either
import com.example.domain.models.offer.OfferDomain
import com.example.domain.repositories.OfferRepository

class OfferRepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val dataSource: DataSource
) : OfferRepository {
    override suspend fun loadOffer(): Either<List<OfferDomain>, Exception> {

        val imageList = listOf(R.drawable.offer1, R.drawable.offer2, R.drawable.offer3)

        return try {
            when (val result = apiDataSource.loadOffer()) {
                is Either.Success -> {
                    val offerList = result.value.map { offerModel ->
                        offerModel.mapToDomain()
                    }

                    dataSource.clear()

                    offerList.forEachIndexed { index, offerDomain ->
                        offerDomain.image = imageList[index]
                    }

                    val offerEntityList = offerList.map {
                        it.mapToDataBase()
                    }

                    offerList.forEach {
                        dataSource.insertOfferPrice(
                            item = PriceEntity(
                                id = it.id,
                                value = it.price.value
                            )
                        )
                    }

                    dataSource.insertOffer(offerEntityList)

                    Either.Success(offerList)
                }

                is Either.Failure -> {
                    Either.Failure(result.error)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Failure(e)
        }
    }

    override suspend fun loadOfferFromDB(): List<OfferDomain> {
        return dataSource.getAllOffers().map {
            it.mapToDomain()
        }
    }
}