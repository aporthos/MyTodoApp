package net.portes.shared.ui.base

import androidx.lifecycle.ViewModel
import net.portes.shared.models.Failure
import net.portes.shared.R

/**
 * @author amadeus.portes
 */
abstract class BaseViewModel : ViewModel() {
    protected fun proccessError(failure: Failure): Int {
        return when (failure) {
            is Failure.NetworkConnection -> {
                R.string.message_not_network
            }
            is Failure.TimeoutError -> {
                R.string.message_timeout_error
            }
            else -> {
                R.string.message_unknow_error
            }
        }
    }
}