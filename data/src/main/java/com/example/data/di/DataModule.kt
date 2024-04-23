package com.example.data.di

import androidx.room.Room
import com.example.data.localDb.DataBase
import com.example.data.localDb.dataSource.DataSource
import com.example.data.localDb.dataSource.DataSourceImpl
import com.example.data.network.api.ApiClient
import com.example.data.network.api.ApiService
import com.example.data.network.apiDataSource.ApiDataSource
import com.example.data.network.apiDataSource.ApiDataSourceImpl
import com.example.data.repositories.DirectFlightsRepositoryImpl
import com.example.data.repositories.OfferRepositoryImpl
import com.example.data.repositories.TicketsRepositoryImpl
import com.example.data.sharedPreferences.Preferences
import com.example.domain.repositories.DirectFlightsRepository
import com.example.domain.repositories.OfferRepository
import com.example.domain.repositories.PreferencesRepository
import com.example.domain.repositories.TicketsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(), DataBase::class.java,
            "Db"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<DataBase>().offerDao() }

    single<DataSource> { DataSourceImpl(get()) }

    single<ApiService> { ApiClient().api }

    single<PreferencesRepository> { Preferences(get()) }

    single<ApiDataSource> { ApiDataSourceImpl(get()) }

    single<OfferRepository> { OfferRepositoryImpl(get(), get()) }

    single<DirectFlightsRepository> { DirectFlightsRepositoryImpl(get()) }

    single<TicketsRepository> { TicketsRepositoryImpl(get()) }

}