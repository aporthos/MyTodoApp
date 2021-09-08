package net.portes.shared.extensions

import java.util.Date
import java.util.Calendar

/**
 * @author amadeus.portes
 */

fun Date.addHours(hours: Int = 24): Date {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    calendar.add(Calendar.HOUR_OF_DAY, hours)
    return calendar.time
}
