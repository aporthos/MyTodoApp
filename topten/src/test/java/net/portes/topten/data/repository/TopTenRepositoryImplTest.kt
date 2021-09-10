package net.portes.topten.data.repository

import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.topten.domain.datasource.TopTenDataSource
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class TopTenRepositoryImplTest {

    @Mock
    private lateinit var dataSource: TopTenDataSource

    private val repository by lazy {
        TopTenRepositoryImpl(dataSource)
    }

    @Test
    fun `validate repository get ipc success`() {
        // Given
        Mockito.`when`(dataSource.getTopTen()).thenReturn(Either.Right(emptyList()))

        // When
        val data = repository.getTopTen()

        // Then
        assertEquals(true, data is Either.Right)
    }

    @Test
    fun `validate repository get ipc failed`() {
        // Given
        Mockito.`when`(dataSource.getTopTen()).thenReturn(Either.Left(Failure.ServerError))

        // When
        val data = repository.getTopTen()

        // Then
        assertEquals(true, data is Either.Left)
    }

    @Test
    fun `validate repository get ipc network not available`() {
        // Given
        Mockito.`when`(dataSource.getTopTen()).thenReturn(Either.Left(Failure.ServerError))

        // When
        val data = repository.getTopTen()

        // Then
        assertEquals(true, data is Either.Left)
    }

}