package net.portes.topten

import net.portes.topten.data.models.response.TopTenResponse

/**
 * @author amadeus.portes
 */
object MockFactory {
    val responseList = arrayListOf(
        responseDowns(),
        responseUps(),
        responseVolume()
    )

    fun responseDowns() = TopTenResponse(
        issueId = "AGUA *",
        openPrice = 24.62f,
        maxPrice = 24.62f,
        minPrice = 23.54f,
        percentageChange = -3.55857656937225f,
        valueChange = -0.59f,
        aggregatedVolume = 621051,
        bidPrice = 24.1f,
        bidVolume = 2730,
        askPrice = 24.42f,
        askVolume = 4000,
        ipcParticipationRate = 0.0f,
        lastPrice = 24.42f,
        closePrice = 25.01f,
        riseLowTypeId = 1,
        instrumentTypeId = 0,
        benchmarkId = 0,
        benchmarkPercentage = 0
    )

    fun responseUps() = TopTenResponse(
        issueId = "FIBRAMQ 12",
        openPrice = 24.62f,
        maxPrice = 24.62f,
        minPrice = 23.54f,
        percentageChange = -3.55857656937225f,
        valueChange = -0.59f,
        aggregatedVolume = 621051,
        bidPrice = 24.1f,
        bidVolume = 2730,
        askPrice = 24.42f,
        askVolume = 4000,
        ipcParticipationRate = 0.0f,
        lastPrice = 24.42f,
        closePrice = 25.01f,
        riseLowTypeId = 2,
        instrumentTypeId = 0,
        benchmarkId = 0,
        benchmarkPercentage = 0
    )

    fun responseVolume() = TopTenResponse(
        issueId = "NEMAK A",
        openPrice = 24.62f,
        maxPrice = 24.62f,
        minPrice = 23.54f,
        percentageChange = -3.55857656937225f,
        valueChange = -0.59f,
        aggregatedVolume = 621051,
        bidPrice = 24.1f,
        bidVolume = 2730,
        askPrice = 24.42f,
        askVolume = 4000,
        ipcParticipationRate = 0.0f,
        lastPrice = 24.42f,
        closePrice = 25.01f,
        riseLowTypeId = 3,
        instrumentTypeId = 0,
        benchmarkId = 0,
        benchmarkPercentage = 0
    )


}