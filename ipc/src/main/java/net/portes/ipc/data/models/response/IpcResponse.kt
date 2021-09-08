package net.portes.ipc.data.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author amadeus.portes
 */
@JsonClass(generateAdapter = true)
data class IpcResponse(
    @Json(name = "date")
    val date: String,
    @Json(name = "price")
    val price: Float,
    @Json(name = "percentageChange")
    val percentageChange: Float,
    @Json(name = "volume")
    val volume: Int,
    @Json(name = "change")
    val change: Float
)