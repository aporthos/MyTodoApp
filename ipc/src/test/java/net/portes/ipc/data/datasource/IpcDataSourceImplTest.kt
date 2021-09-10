package net.portes.ipc.data.datasource

import net.portes.ipc.data.mapper.IpcListMapper
import net.portes.ipc.data.mapper.IpcMapper
import net.portes.ipc.data.models.response.IpcResponse
import net.portes.ipc.data.services.IpcService
import net.portes.shared.NetworkHandler
import net.portes.shared.models.Either
import net.portes.shared.util.anyError
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response
import retrofit2.Response.error

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class IpcDataSourceImplTest {

    @Mock
    private lateinit var service: IpcService

    @Mock
    private lateinit var networkHandler: NetworkHandler

    @Mock
    private lateinit var call: Call<List<IpcResponse>>

    private val mapper by lazy {
        IpcListMapper(IpcMapper())
    }

    private val dataSource by lazy {
        IpcDataSourceImpl(service, networkHandler, mapper)
    }

    @Test
    fun `validate data source get ipc success`() {
        // Given
        `when`(call.execute()).thenReturn(Response.success(emptyList()))
        `when`(service.getIpc()).thenReturn(call)
        `when`(networkHandler.isNetworkAvailable()).thenReturn(true)

        // When
        val data = dataSource.getIpc()

        // Then
        assertEquals(true, data is Either.Right)
    }

    @Test
    fun `validate data source get ipc failed`() {
        // Given
        `when`(call.execute()).thenReturn(error(401, anyError()))
        `when`(service.getIpc()).thenReturn(call)
        `when`(networkHandler.isNetworkAvailable()).thenReturn(true)

        // When
        val data = dataSource.getIpc()

        //Then
        assertEquals(true, data is Either.Left)
    }

    @Test
    fun `validate data source get ipc is network not available`() {
        // Given
        `when`(networkHandler.isNetworkAvailable()).thenReturn(false)

        // When
        val data = dataSource.getIpc()

        //Then
        assertEquals(true, data is Either.Left)
    }

}