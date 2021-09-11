package net.portes.login.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.portes.login.domain.datasource.LoginFirebaseDataSource
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import org.junit.Assert.assertEquals
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
class LoginFirebaseRepositoryImplTest {

    @Mock
    private lateinit var dataSource: LoginFirebaseDataSource

    private val repository by lazy {
        LoginFirebaseRepositoryImpl(dataSource)
    }

    @Test
    fun `validate repository login success`() = runBlockingTest {
        // Given
        `when`(dataSource.login()).thenReturn(Either.Right(true))

        // When
        val data = repository.login()

        // Then
        assertEquals(true, data is Either.Right)
    }

    @Test
    fun `validate repository get ipc failed`() = runBlockingTest {
        // Given
        `when`(dataSource.login()).thenReturn(Either.Left(Failure.ServerError))

        // When
        val data = repository.login()

        // Then
        assertEquals(true, data is Either.Left)
    }

    @Test
    fun `validate repository get ipc network not available`() = runBlockingTest {
        // Given
        `when`(dataSource.login()).thenReturn(Either.Left(Failure.ServerError))

        // When
        val data = repository.login()

        // Then
        assertEquals(true, data is Either.Left)
    }

}