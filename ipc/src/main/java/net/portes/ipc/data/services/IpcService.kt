package net.portes.ipc.data.services

import net.portes.ipc.data.models.response.IpcResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author amadeus.portes
 */
interface IpcService {
    @GET("v3/cc4c350b-1f11-42a0-a1aa-f8593eafeb1e")
    fun getIpc(): Call<List<IpcResponse>>
}