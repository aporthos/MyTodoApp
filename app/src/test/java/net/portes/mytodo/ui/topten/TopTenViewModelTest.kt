package net.portes.mytodo.ui.topten

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.portes.mytodo.ui.ipc.MainCoroutineRule
import net.portes.mytodo.ui.ipc.runBlockingTest
import net.portes.shared.extensions.getThisValue
import net.portes.shared.models.Either
import net.portes.shared.ui.base.ViewState
import net.portes.topten.domain.usecases.GetTopTenUseCase
import org.junit.Assert.assertTrue
import org.junit.Ignore
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
class TopTenViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getTopTenUseCase: GetTopTenUseCase

    private val viewModel by lazy {
        TopTenViewModel(getTopTenUseCase)
    }

    @Ignore
    @Test
    fun `validate get list ipc success`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        `when`(getTopTenUseCase(Unit)).thenReturn(Either.Right(emptyList()))

        // When
        viewModel.startUpdates()

        // Then
        assertTrue(viewModel.topTenResponse.getThisValue() is ViewState.Loading)
        viewModel.stopUpdates()
        mainCoroutineRule.testDispatcher.resumeDispatcher()

    }

}