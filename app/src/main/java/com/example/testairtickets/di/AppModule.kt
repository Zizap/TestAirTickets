package com.example.testairtickets.di

import com.example.testairtickets.screens.mainScreen.MainScreenViewModel
import com.example.testairtickets.screens.searchScreen.SearchScreenViewModel
import com.example.testairtickets.screens.ticketsScreen.TicketsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainScreenViewModel(get(), get()) }

    viewModel { SearchScreenViewModel(get()) }

    viewModel { TicketsScreenViewModel(get()) }
}