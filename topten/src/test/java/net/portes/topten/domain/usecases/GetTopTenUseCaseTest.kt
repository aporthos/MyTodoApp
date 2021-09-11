package net.portes.topten.domain.usecases

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.topten.domain.repository.TopTenRepository
import org.junit.Assert.*
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
class GetTopTenUseCaseTest {

    @Mock
    private lateinit var repository: TopTenRepository

    private val useCase by lazy {
        GetTopTenUseCase(TestCoroutineDispatcher(), repository)
    }

    @Test
    fun `validation use case success`() = runBlockingTest {
        `when`(repository.getTopTen()).thenReturn(Either.Right(emptyList()))

        val ipcUseCase = useCase(Unit)
        assertEquals(true, ipcUseCase is Either.Right)
    }

    @Test
    fun `validation use case failed`() = runBlockingTest {
        `when`(repository.getTopTen()).thenReturn(Either.Left(Failure.ServerError))

        val ipcUseCase = useCase(Unit)
        assertEquals(true, ipcUseCase is Either.Left)
    }

}