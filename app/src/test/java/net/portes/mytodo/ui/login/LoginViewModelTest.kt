package net.portes.mytodo.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.portes.login.domain.usecases.LoginFirebaseUseCase
import net.portes.mytodo.ui.ipc.MainCoroutineRule
import net.portes.mytodo.ui.ipc.runBlockingTest
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
class LoginViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginFirebaseUseCase: LoginFirebaseUseCase

    private val viewModel by lazy {
        LoginViewModel(loginFirebaseUseCase)
    }

    @Test
    fun `validate get list ipc success`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        `when`(loginFirebaseUseCase(Unit)).thenReturn(Either.Right(true))

        // When
        viewModel.toLoginFirebase()

        // Then
        assertTrue(viewModel.loginFirebaseResponse.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        assertTrue(viewModel.loginFirebaseResponse.getThisValue() is ViewState.Success)
    }

    @Test
    fun `validate get list ipc failed`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        `when`(loginFirebaseUseCase(Unit)).thenReturn(Either.Left(Failure.ServerError))

        // When
        viewModel.toLoginFirebase()

        // Then
        assertTrue(viewModel.loginFirebaseResponse.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        assertTrue(viewModel.loginFirebaseResponse.getThisValue() is ViewState.Error)
    }

}