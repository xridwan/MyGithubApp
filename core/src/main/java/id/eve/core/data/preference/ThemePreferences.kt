package id.eve.core.data.preference

import kotlinx.coroutines.flow.Flow

interface ThemePreferences {
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
}