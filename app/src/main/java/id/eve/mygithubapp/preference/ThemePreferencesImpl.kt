package id.eve.mygithubapp.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import id.eve.core.data.preference.ThemePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemePreferencesImpl(private val dataStore: DataStore<Preferences>) : ThemePreferences {

    private val themeKey = booleanPreferencesKey("theme_setting")

    override fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[themeKey] ?: false
        }
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[themeKey] = isDarkModeActive
        }
    }

//    companion object {
//        @Volatile
//        private var INSTANCE: ThemePreferences? = null
//
//        fun getInstance(dataStore: DataStore<Preferences>): ThemePreferences {
//            return INSTANCE ?: synchronized(this) {
//                val instance = ThemePreferences(dataStore)
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}