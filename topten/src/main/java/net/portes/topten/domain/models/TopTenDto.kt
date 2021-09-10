package net.portes.topten.domain.models

/**
 * @author amadeus.portes
 */
data class TopTenDto(
    val aggregatedVolume: Int,
    val askPrice: Float,
    val askVolume: Int,
    val benchmarkId: Int,
    val benchmarkPercentage: Int,
    val bidPrice: Float,
    val bidVolume: Int,
    val closePrice: Float,
    val instrumentTypeId: Int,
    val ipcParticipationRate: Float,
    val issueId: String,
    val lastPrice: Float,
    val maxPrice: Float,
    val minPrice: Float,
    val openPrice: Float,
    val percentageChange: Float,
    val riseLowTypeId: Int,
    val valueChange: Float
)