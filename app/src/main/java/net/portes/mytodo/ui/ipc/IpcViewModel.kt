package net.portes.mytodo.ui.ipc

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import net.portes.shared.models.Failure
import kotlinx.coroutines.launch
import net.portes.ipc.domain.model.IpcDto
import net.portes.ipc.domain.usecases.GetIpcUseCase
import net.portes.shared.extensions.addHours
import net.portes.shared.extensions.sumByFloat
import net.portes.shared.ui.base.BaseViewModel
import net.portes.shared.ui.base.ViewState
import java.util.*

class IpcViewModel @ViewModelInject constructor(private val getIpcUseCase: GetIpcUseCase): BaseViewModel() {

    private val _ipcResponse = MutableLiveData<ViewState<List<IpcDto>>>()
    val ipcResponse: LiveData<ViewState<List<IpcDto>>>
        get() = _ipcResponse
    
    private val _totalBalance = MutableLiveData<Float>()
    val totalBalance: LiveData<Float>
        get() = _totalBalance

    private var ipcList = mutableListOf<IpcDto>()

    fun getListIpc() {
        _ipcResponse.value = ViewState.Loading()
        viewModelScope.launch {
            getIpcUseCase(Unit).fold(::toGetIpcFailure, ::toGetIpcSuccess)
        }
    }

    private fun toGetIpcSuccess(ipcList: List<IpcDto>) {
        _ipcResponse.value = ViewState.Success(ipcList)
        _totalBalance.value = ipcList.sumByFloat { it.price }
        this.ipcList = ipcList.toMutableList()
    }

    private fun toGetIpcFailure(failure: Failure) {
        _ipcResponse.value = ViewState.Error(proccessError(failure))
    }

    fun toFilter(filter: Int) {
        val date = Date().addHours(filter).time
        val ipcListFilter = ipcList.filter { it.dateTimeStamp < date }
        _ipcResponse.value = ViewState.Success(ipcListFilter)
    }

}