package com.xridwan.mygithub.di

import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import id.eve.core.data.preference.ReminderPreference
import id.eve.core.data.preference.ThemePreferences
import id.eve.core.domain.usecase.UserInteractor
import id.eve.core.domain.usecase.UserUseCase
import id.eve.mygithubapp.preference.ReminderPreferenceImpl
import id.eve.mygithubapp.preference.ThemePreferencesImpl
import id.eve.mygithubapp.presenter.detail.DetailViewModel
import id.eve.mygithubapp.presenter.follower.FollowersViewModel
import id.eve.mygithubapp.presenter.following.FollowingViewModel
import id.eve.mygithubapp.presenter.main.MainViewModel
import id.eve.mygithubapp.presenter.main.ThemeViewModel
import id.eve.mygithubapp.presenter.other.ReminderViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val prefsModule = module {
    single {
        val appContext = androidContext()
        val userPref = "settings"
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext, userPref)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {
                appContext.preferencesDataStoreFile(userPref)
            }
        )
    }
    single<ThemePreferences> {
        ThemePreferencesImpl(get())
    }
    single<ReminderPreference> {
        ReminderPreferenceImpl(androidApplication())
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ThemeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FollowersViewModel(get()) }
    viewModel { FollowingViewModel(get()) }
    viewModel { ReminderViewModel(get()) }
}

val useCaseModule = module {
    single<UserUseCase> {
        UserInteractor(get())
    }
}