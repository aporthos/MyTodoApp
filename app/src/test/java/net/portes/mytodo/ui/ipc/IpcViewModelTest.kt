package net.portes.mytodo.ui.ipc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.portes.ipc.domain.usecases.GetIpcUseCase
import net.portes.shared.extensions.getThisValue
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.ViewState
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class IpcViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getIpcUseCase: GetIpcUseCase

    private val viewModel by lazy {
        IpcViewModel(getIpcUseCase)
    }

    @Test
    fun `validate get list ipc success`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        `when`(getIpcUseCase(Unit)).thenReturn(Either.Right(emptyList()))

        // When
        viewModel.getListIpc()

        // Then
        assertTrue(viewModel.ipcResponse.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        assertTrue(viewModel.ipcResponse.getThisValue() is ViewState.Success)
        assertEquals(viewModel.totalBalance.getThisValue(), 0.0f)
    }

    @Test
    fun `validate get list ipc failed`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        `when`(getIpcUseCase(Unit)).thenReturn(Either.Left(Failure.ServerError))

        // When
        viewModel.getListIpc()

        // Then
        assertTrue(viewModel.ipcResponse.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        assertTrue(viewModel.ipcResponse.getThisValue() is ViewState.Error)
    }

    @Test
    fun `validate filter ipc`() {
        // When
        viewModel.toFilter(4)

        // Then
        assertTrue(viewModel.ipcResponse.getThisValue() is ViewState.Success)
    }


}