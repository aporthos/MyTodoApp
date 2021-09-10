package net.portes.topten.data.datasource

import net.portes.shared.NetworkHandler
import net.portes.shared.models.Either
import net.portes.shared.util.anyError
import net.portes.topten.data.mapper.TopTenListMapper
import net.portes.topten.data.mapper.TopTenMapper
import net.portes.topten.data.models.response.TopTenResponse
import net.portes.topten.data.services.TopTenService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class TopTenDataSourceImplTest {

    @Mock
    private lateinit var service: TopTenService

    @Mock
    private lateinit var networkHandler: NetworkHandler

    @Mock
    private lateinit var call: Call<List<TopTenResponse>>

    private val mapper by lazy {
        TopTenListMapper(TopTenMapper())
    }

    private val dataSource by lazy {
        TopTenDataSourceImpl(service, networkHandler, mapper)
    }

    @Test
    fun `validate data source get Top Ten success`() {
        // Given
        Mockito.`when`(call.execute()).thenReturn(Response.success(emptyList()))
        Mockito.`when`(service.getTopTen()).thenReturn(call)
        Mockito.`when`(networkHandler.isNetworkAvailable()).thenReturn(true)

        // When
        val data = dataSource.getTopTen()

        // Then
        Assert.assertEquals(true, data is Either.Right)
    }

    @Test
    fun `validate data source get ipc failed`() {
        // Given
        Mockito.`when`(call.execute()).thenReturn(Response.error(401, anyError()))
        Mockito.`when`(service.getTopTen()).thenReturn(call)
        Mockito.`when`(networkHandler.isNetworkAvailable()).thenReturn(true)

        // When
        val data = dataSource.getTopTen()

        //Then
        Assert.assertEquals(true, data is Either.Left)
    }

    @Test
    fun `validate data source get ipc is network not available`() {
        // Given
        Mockito.`when`(networkHandler.isNetworkAvailable()).thenReturn(false)

        // When
        val data = dataSource.getTopTen()

        //Then
        Assert.assertEquals(true, data is Either.Left)
    }

}