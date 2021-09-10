package net.portes.mytodo.ui.topten

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.BaseViewModel
import net.portes.shared.ui.base.ViewState
import net.portes.topten.domain.models.TopTenItem
import net.portes.topten.domain.usecases.GetTopTenUseCase

class TopTenViewModel @ViewModelInject constructor(private val getTopTenUseCase: GetTopTenUseCase) :
    BaseViewModel() {

    companion object {
        private const val REFRESH_TIME = 10_000L
    }

    private var job: Job? = null
    private val _topTenResponse = MutableLiveData<ViewState<List<TopTenItem>>>()
    val topTenResponse: LiveData<ViewState<List<TopTenItem>>>
        get() = _topTenResponse

    fun startUpdates() {
        stopUpdates()
        job = viewModelScope.launch {
            while(isActive) {
                getTopTenList()
                delay(REFRESH_TIME)
            }
        }
    }

    private suspend fun getTopTenList() {
        _topTenResponse.value = ViewState.Loading()
        getTopTenUseCase(Unit).fold(::toGetTopTenFailure, ::toGetTopTenSuccess)
    }

    private fun toGetTopTenSuccess(topTenList: List<TopTenItem>) {
        _topTenResponse.value = ViewState.Success(topTenList)
    }

    private fun toGetTopTenFailure(failure: Failure) {
        _topTenResponse.value = ViewState.Error(proccessError(failure))
    }

    override fun onCleared() {
        super.onCleared()
        stopUpdates()
    }

    fun stopUpdates() {
        job?.cancel()
        job = null
    }

}