package net.portes.shared.util

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import net.portes.shared.R
import net.portes.shared.extensions.parsePercentage

/**
 * @author amadeus.portes
 */
object BindingAdapters {

    @JvmStatic
    @BindingAdapter("formatTextColor", "formatText")
    fun formatPercentage(view: TextView, type: Int, formatText: Float) {
        if (type == TYPE_UPS || type == TYPE_VOLUME) {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
        } else {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.red))
        }
        view.text = formatText.parsePercentage()
    }

}