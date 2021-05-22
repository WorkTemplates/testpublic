package com.template.app.koin

import com.template.app.network.*
import com.template.app.repository.AccessRepository
import com.template.app.repository.AccessRepositoryImpl
import com.template.app.repository.NetworkRepository
import com.template.app.repository.NetworkRepositoryImpl
import com.template.app.ui.main.MainViewModel
import com.template.app.ui.main.data.PlayersViewModel
import com.template.app.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { PlayersViewModel(get()) }
    viewModel { SplashViewModel(get()) }
}

val networkModule = module {
    single<DataRetrofit> { createPlayersRetrofit() }
    single<AccessRetrofit> { createAccessRetrofit() }
    single<AccessGeoRetrofit> { createAccessGeoRetrofit() }
}

val repoModule = module {
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
    single<AccessRepository> { AccessRepositoryImpl(get(), get()) }
}

val appModules = networkModule + repoModule + viewModelModule
