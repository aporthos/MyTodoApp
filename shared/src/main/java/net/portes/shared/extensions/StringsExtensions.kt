package net.portes.shared.extensions

import android.util.Patterns

/**
 * @author amadeus.portes
 */
fun String.isValidUrl(): Boolean = Patterns.WEB_URL.matcher(this).matches()
