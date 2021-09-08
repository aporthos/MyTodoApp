package net.portes.ipc.data.repository

import net.portes.ipc.domain.datasource.IpcDataSource
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
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class IpcRepositoryImplTest {

    @Mock
    private lateinit var dataSource: IpcDataSource

    private val repository by lazy {
        IpcRepositoryImpl(dataSource)
    }

    @Test
    fun `validate repository get ipc success`() {
        // Given
        `when`(dataSource.getIpc()).thenReturn(Either.Right(emptyList()))

        // When
        val data = repository.getIpc()

        // Then
        assertEquals(true, data is Either.Right)
    }

    @Test
    fun `validate repository get ipc failed`() {
        // Given
        `when`(dataSource.getIpc()).thenReturn(Either.Left(Failure.ServerError))

        // When
        val data = repository.getIpc()

        // Then
        assertEquals(true, data is Either.Left)
    }

    @Test
    fun `validate repository get ipc network not available`() {
        // Given
        `when`(dataSource.getIpc()).thenReturn(Either.Left(Failure.ServerError))

        // When
        val data = repository.getIpc()

        // Then
        assertEquals(true, data is Either.Left)
    }

}