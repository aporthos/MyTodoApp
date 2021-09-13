package net.portes.config.prefs

import android.content.Context
import android.content.SharedPreferences
import net.portes.config.R
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class ConfigSharedPref @Inject constructor(private val context: Context) {
    companion object {
        private const val NAME_CONFIG_APP_SHARED_PREF = "CONFIG_APP"
        private const val KEY_MESSAGE_SHARE_DEFAULT = "KEY_MESSAGE_SHARE_DEFAULT"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(NAME_CONFIG_APP_SHARED_PREF, Context.MODE_PRIVATE)

    var messageShareDefault: String
        get() = preferences.getString(
            KEY_MESSAGE_SHARE_DEFAULT,
            context.getString(R.string.message_share_default)
        ) ?: context.getString(R.string.message_share_default)
        set(value) = preferences.edit().putString(KEY_MESSAGE_SHARE_DEFAULT, value).apply()
}