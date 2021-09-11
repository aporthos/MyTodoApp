package net.portes.mytodo.ui.login

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class LoginSharedPref @Inject constructor(context: Context) {
    companion object {
        private const val NAME_LOGIN_SHARED_PREF = "SESION_USER"
        private const val KEY_IS_LOGGIN = "KEY_IS_LOGGIN"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(NAME_LOGIN_SHARED_PREF, Context.MODE_PRIVATE)

    var isLoggin: Boolean
        get() = preferences.getBoolean(KEY_IS_LOGGIN, false)
        set(value) = preferences.edit().putBoolean(KEY_IS_LOGGIN, value).apply()
}