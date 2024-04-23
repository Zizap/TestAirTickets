package com.example.domain.di

import com.example.domain.usecase.DirectFlightsUseCase
import com.example.domain.usecase.OfferUseCase
import com.example.domain.usecase.PreferencesUseCase
import com.example.domain.usecase.TicketsUseCase
import org.koin.dsl.module

val domainModule = module {

    single { OfferUseCase(get()) }

    single { PreferencesUseCase(get()) }

    single { DirectFlightsUseCase(get()) }

    single { TicketsUseCase(get()) }

}