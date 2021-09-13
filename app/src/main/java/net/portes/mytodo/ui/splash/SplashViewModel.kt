package net.portes.mytodo.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.portes.ipc.domain.usecases.GetIpcUrlUseCase
import net.portes.shared.models.Failure

/**
 * @author amadeus.portes
 */
class SplashViewModel @ViewModelInject constructor(private val getIpcUrlUseCase: GetIpcUrlUseCase) :
    ViewModel() {

    private val _ipcUrlResponse = MutableLiveData<Boolean>()
    val ipcUrl: LiveData<Boolean>
        get() = _ipcUrlResponse

    fun getIpcUrl() {
        viewModelScope.launch {
            getIpcUrlUseCase(Unit).fold(::toGetIpcUrlDefault, ::toGetIpcUrlSuccess)
        }
    }

    private fun toGetIpcUrlSuccess(result: Boolean) {
        _ipcUrlResponse.value = result
    }

    private fun toGetIpcUrlDefault(failure: Failure) {
        _ipcUrlResponse.value = false
    }
}