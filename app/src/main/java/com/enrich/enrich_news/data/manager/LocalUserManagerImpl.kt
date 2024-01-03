package com.enrich.enrich_news.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.enrich.enrich_news.domain.manager.LocalUserManager
import com.enrich.enrich_news.util.Constants
import com.enrich.enrich_news.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of LocalUserManager to handle saving and reading the app entry state using DataStore.
 */
class LocalUserManagerImpl(
    private val context: Context,
) : LocalUserManager {
    /**
     * Saves the app entry state.
     */
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PrefKeys.APP_ENTRY] = true
        }
    }

    /**
     * Reads the app entry state.
     *
     * @return A Flow emitting the app entry state as a Boolean.
     */
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[PrefKeys.APP_ENTRY] ?: false
        }
    }
}

/**
 * Extension property to get a DataStore instance associated with the Context.
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

/**
 * Object defining keys for preferences in DataStore.
 */
private object PrefKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}