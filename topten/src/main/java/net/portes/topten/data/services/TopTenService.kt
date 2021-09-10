package net.portes.topten.data.services

import net.portes.topten.data.models.response.TopTenResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author amadeus.portes
 */
interface TopTenService {
    @GET("v3/b4eb963c-4aee-4b60-a378-20cb5b00678f")
    fun getTopTen(): Call<List<TopTenResponse>>
}