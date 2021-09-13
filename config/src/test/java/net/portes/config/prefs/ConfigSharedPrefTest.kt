package net.portes.config.prefs

import android.content.Context
import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class ConfigSharedPrefTest {

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Mock
    private lateinit var context: Context

    private val sharedPref by lazy {
        ConfigSharedPref(context)
    }

    @Before
    fun setUp() {
        `when`(context.getSharedPreferences("CONFIG_APP", Context.MODE_PRIVATE)).thenReturn(
            sharedPreferences
        )
        `when`(context.getString(anyInt())).thenReturn("")
        `when`(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
    }

    @Test
    fun `validation save message share default`() {
        `when`(sharedPreferences.edit().putString(anyString(), anyString())).thenReturn(
            sharedPreferencesEditor
        )
        sharedPref.messageShareDefault = "Nuevo valor"
        verify(sharedPreferencesEditor)
            .putString(eq("KEY_MESSAGE_SHARE_DEFAULT"), eq("Nuevo valor"))
        verify(sharedPreferencesEditor).apply()
    }

    @Test
    fun `validation get message share default`() {
        sharedPref.messageShareDefault

        verify(sharedPreferencesEditor, never())
            .putString(anyString(), anyString())
        verify(sharedPreferencesEditor, never()).apply()
        verify(sharedPreferences).getString(anyString(), anyString())
    }

}