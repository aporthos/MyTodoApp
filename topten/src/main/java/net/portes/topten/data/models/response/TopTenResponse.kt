package net.portes.topten.data.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
/**
 * @author amadeus.portes
 */
@JsonClass(generateAdapter = true)
data class TopTenResponse(
    @Json(name = "aggregatedVolume")
    val aggregatedVolume: Int,
    @Json(name = "askPrice")
    val askPrice: Float,
    @Json(name = "askVolume")
    val askVolume: Int,
    @Json(name = "benchmarkId")
    val benchmarkId: Int,
    @Json(name = "benchmarkPercentage")
    val benchmarkPercentage: Int,
    @Json(name = "bidPrice")
    val bidPrice: Float,
    @Json(name = "bidVolume")
    val bidVolume: Int,
    @Json(name = "closePrice")
    val closePrice: Float,
    @Json(name = "instrumentTypeId")
    val instrumentTypeId: Int,
    @Json(name = "ipcParticipationRate")
    val ipcParticipationRate: Float,
    @Json(name = "issueId")
    val issueId: String,
    @Json(name = "lastPrice")
    val lastPrice: Float,
    @Json(name = "maxPrice")
    val maxPrice: Float,
    @Json(name = "minPrice")
    val minPrice: Float,
    @Json(name = "openPrice")
    val openPrice: Float,
    @Json(name = "percentageChange")
    val percentageChange: Float,
    @Json(name = "riseLowTypeId")
    val riseLowTypeId: Int,
    @Json(name = "valueChange")
    val valueChange: Float
)