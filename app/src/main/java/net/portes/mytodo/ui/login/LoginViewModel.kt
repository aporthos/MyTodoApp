package net.portes.mytodo.ui.login

import androidx.lifecycle.ViewModel
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.portes.login.domain.usecases.LoginFirebaseUseCase
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.ViewState

class LoginViewModel @ViewModelInject constructor(private val loginFirebaseUseCase: LoginFirebaseUseCase) :
    ViewModel() {

    private val _loginFirebaseResponse = MutableLiveData<ViewState<Boolean>>()
    val loginFirebaseResponse: LiveData<ViewState<Boolean>>
        get() = _loginFirebaseResponse

    fun toLoginFirebase() {
        _loginFirebaseResponse.value = ViewState.Loading()
        viewModelScope.launch {
            loginFirebaseUseCase(Unit).fold(::toLoginFirebaseFailure, ::toLoginFirebaseSuccess)
        }
    }

    private fun toLoginFirebaseSuccess(ipcList: Boolean) {
        _loginFirebaseResponse.value = ViewState.Success(ipcList)
    }

    private fun toLoginFirebaseFailure(failure: Failure) {
        _loginFirebaseResponse.value = ViewState.Error(-1)
    }
}