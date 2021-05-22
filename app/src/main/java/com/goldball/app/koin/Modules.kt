package com.goldball.app.koin

import com.goldball.app.network.*
import com.goldball.app.repository.AccessRepository
import com.goldball.app.repository.AccessRepositoryImpl
import com.goldball.app.repository.PlayersRepository
import com.goldball.app.repository.PlayersRepositoryImpl
import com.goldball.app.ui.main.MainViewModel
import com.goldball.app.ui.main.players.PlayersViewModel
import com.goldball.app.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { PlayersViewModel(get()) }
    viewModel { SplashViewModel(get()) }
}

val networkModule = module {
    single<PlayersRetrofit> { createPlayersRetrofit() }
    single<AccessRetrofit> { createAccessRetrofit() }
    single<AccessGeoRetrofit> { createAccessGeoRetrofit() }
}

val repoModule = module {
    single<PlayersRepository> { PlayersRepositoryImpl(get()) }
    single<AccessRepository> { AccessRepositoryImpl(get(), get()) }
}

val appModules = networkModule + repoModule + viewModelModule
