package net.portes.shared.extensions

import net.portes.shared.util.MONEY_FORMAT
import java.text.DecimalFormat

/**
 * @author amadeus.portes
 */
inline fun <T> Iterable<T>.sumByFloat(selector: (T) -> Float): Float {
    var sum = 0.0f
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

fun Float.parseMoney(): String {
    val formatter = DecimalFormat(MONEY_FORMAT)
    return "$${formatter.format(this)}"
}