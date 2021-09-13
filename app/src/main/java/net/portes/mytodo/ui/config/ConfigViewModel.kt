package net.portes.mytodo.ui.config

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import net.portes.config.domain.usecases.UpdateMessageDefaultUseCase
import net.portes.config.domain.usecases.GetMessageDefaultUseCase
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.BaseViewModel
import net.portes.shared.ui.base.ViewState

class ConfigViewModel @ViewModelInject constructor(
    private val updateMessageDefaultUseCase: UpdateMessageDefaultUseCase,
    private val getMessageDefaultUseCase: GetMessageDefaultUseCase,
    private var firebaseAuth: FirebaseAuth
) : BaseViewModel() {

    private val _messageShareUpdateResponse = MutableLiveData<ViewState<Unit>>()
    val messageShareUpdateResponse: LiveData<ViewState<Unit>>
        get() = _messageShareUpdateResponse

    private val _messageShareResponse = MutableLiveData<ViewState<String>>()
    val messageShareResponse: LiveData<ViewState<String>>
        get() = _messageShareResponse

    private val _toLogout = MutableLiveData<Unit>()
    val toLogout: LiveData<Unit>
        get() = _toLogout

    fun updateMessageShareDefault(message: String) {
        _messageShareUpdateResponse.value = ViewState.Loading()
        viewModelScope.launch {
            updateMessageDefaultUseCase(message).fold(::toMessageShareUpdateFailure, ::toMessageShareUpdateSuccess)
        }
    }

    fun getMessageShareDefault() {
        _messageShareResponse.value = ViewState.Loading()
        viewModelScope.launch {
            getMessageDefaultUseCase(Unit).fold(::toMessageShareFailure, ::toMessageShareSuccess)
        }
    }

    fun toLogout() {
        firebaseAuth.signOut()
        _toLogout.value = Unit
    }

    private fun toMessageShareSuccess(result: String) {
        _messageShareResponse.value = ViewState.Success(result)
    }

    private fun toMessageShareFailure(failure: Failure) {
        _messageShareResponse.value = ViewState.Error(proccessError(failure))
    }
    private fun toMessageShareUpdateSuccess(result: Unit) {
        _messageShareUpdateResponse.value = ViewState.Success(result)
    }

    private fun toMessageShareUpdateFailure(failure: Failure) {
        _messageShareUpdateResponse.value = ViewState.Error(proccessError(failure))
    }
}