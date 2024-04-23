package com.example.data.network.apiDataSource

import com.example.data.network.api.ApiService
import com.example.data.network.models.offer.OfferModel
import com.example.data.network.models.directFlights.DirectFlightsModel
import com.example.data.network.models.tickets.TicketsModel
import com.example.domain.models.Either


class ApiDataSourceImpl(
    private val apiService: ApiService
): ApiDataSource {

    override suspend fun loadOffer(): Either<List<OfferModel>, Exception> {
        lateinit var either: Either<List<OfferModel>, Exception>

        val response = apiService.getOffer()

        either = try {
            Either.Success(response.response)
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Failure(e)
        }
        return either
    }

    override suspend fun loadDirectFlights(): Either<List<DirectFlightsModel>, Exception> {
        lateinit var either: Either<List<DirectFlightsModel>, Exception>

        val response = apiService.getDirectFlights()

        either = try {
            Either.Success(response.response)
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Failure(e)
        }
        return either
    }

    override suspend fun loadTickets(): Either<List<TicketsModel>, Exception> {
        lateinit var either: Either<List<TicketsModel>, Exception>

        val response = apiService.getTickets()

        either = try {
            Either.Success(response.response)
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Failure(e)
        }
        return either
    }

}