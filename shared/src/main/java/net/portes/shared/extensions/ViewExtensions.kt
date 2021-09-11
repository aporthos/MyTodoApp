package net.portes.shared.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

/**
 * @author amadeus.portes
 */
inline fun <reified T : Any> Context.intent() = Intent(this, T::class.java)

fun Context.isBiometricSuccess() =
    BiometricManager.from(this).canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS

fun Activity.createFun(
    context: FragmentActivity,
    failed: () -> Unit,
    success: () -> Unit
): BiometricPrompt {
    val executor = ContextCompat.getMainExecutor(context)

    val callback = object : BiometricPrompt.AuthenticationCallback() {

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            failed()
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            success()
        }
    }
    return BiometricPrompt(context, executor, callback)

}