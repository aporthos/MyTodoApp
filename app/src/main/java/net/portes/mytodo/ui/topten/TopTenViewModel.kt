package net.portes.mytodo.ui.topten

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import kotlinx.coroutines.*
import net.portes.mytodo.util.files.DocumentType
import net.portes.mytodo.util.files.DocumentFactory
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.BaseViewModel
import net.portes.shared.ui.base.ViewState
import net.portes.topten.domain.models.TopTenItem
import net.portes.topten.domain.usecases.GetTopTenUseCase
import java.io.File

class TopTenViewModel @ViewModelInject constructor(
    private val getTopTenUseCase: GetTopTenUseCase,
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private var firebaseAuth: FirebaseAuth
) :
    BaseViewModel() {

    companion object {
        private const val KEY_TIME_REFRESH = "timeRefresh"
    }

    private var job: Job? = null
    private val _topTenResponse = MutableLiveData<ViewState<List<TopTenItem>>>()
    val topTenResponse: LiveData<ViewState<List<TopTenItem>>>
        get() = _topTenResponse

    private var topTenItem = mutableListOf<TopTenItem>()

    private val _toLogout = MutableLiveData<Unit>()
    val toLogout: LiveData<Unit>
        get() = _toLogout

    private val _pdfDocument = MutableLiveData<File>()
    val pdfDocument: LiveData<File>
        get() = _pdfDocument

    fun startUpdates() {
        stopUpdates()
        job = viewModelScope.launch {
            while (isActive) {
                getTopTenList()
                delay(firebaseRemoteConfig[KEY_TIME_REFRESH].asLong())
            }
        }
    }

    fun stopUpdates() {
        job?.cancel()
        job = null
    }

    fun toLogout() {
        firebaseAuth.signOut()
        _toLogout.value = Unit
    }

    fun toPdfCreate(context: Context) {
        val pdfDocument = DocumentFactory.getFileFactory(DocumentType.Pdf, context)
        _pdfDocument.value = pdfDocument.create(topTenItem)
    }

    private suspend fun getTopTenList() {
        _topTenResponse.value = ViewState.Loading()
        getTopTenUseCase(Unit).fold(::toGetTopTenFailure, ::toGetTopTenSuccess)
    }

    private fun toGetTopTenSuccess(topTenList: List<TopTenItem>) {
        _topTenResponse.value = ViewState.Success(topTenList)
        topTenItem = topTenList.toMutableList()
    }

    private fun toGetTopTenFailure(failure: Failure) {
        _topTenResponse.value = ViewState.Error(proccessError(failure))
    }

    override fun onCleared() {
        super.onCleared()
        stopUpdates()
    }

}