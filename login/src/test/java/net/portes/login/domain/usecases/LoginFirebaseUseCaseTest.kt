package net.portes.login.domain.usecases

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import net.portes.login.domain.repository.LoginFirebaseRepository
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
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
class LoginFirebaseUseCaseTest {

    @Mock
    private lateinit var repository: LoginFirebaseRepository

    private val useCase by lazy {
        LoginFirebaseUseCase(TestCoroutineDispatcher(), repository)
    }

    @Test
    fun `validation use case success`() = runBlockingTest {
        `when`(repository.login()).thenReturn(Either.Right(true))

        val loginUseCase = useCase(Unit)
        assertEquals(true, loginUseCase is Either.Right)
    }

    @Test
    fun `validation use case failed`() = runBlockingTest {
        `when`(repository.login()).thenReturn(Either.Left(Failure.ServerError))

        val loginUseCase = useCase(Unit)
        assertEquals(true, loginUseCase is Either.Left)
    }

}