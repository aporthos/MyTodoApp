package net.portes.topten.data.mapper

import net.portes.shared.data.mapper.BaseMapper
import net.portes.topten.data.models.response.TopTenResponse
import net.portes.topten.domain.models.TopTenDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class TopTenMapper @Inject constructor() : BaseMapper<TopTenResponse, TopTenDto> {
    override fun mapFrom(from: TopTenResponse): TopTenDto = TopTenDto(
        aggregatedVolume = from.aggregatedVolume,
        askPrice = from.askPrice,
        askVolume = from.askVolume,
        benchmarkId = from.benchmarkId,
        benchmarkPercentage = from.benchmarkPercentage,
        bidPrice = from.bidPrice,
        bidVolume = from.bidVolume,
        closePrice = from.closePrice,
        instrumentTypeId = from.instrumentTypeId,
        ipcParticipationRate = from.ipcParticipationRate,
        issueId = from.issueId,
        lastPrice = from.lastPrice,
        maxPrice = from.maxPrice,
        minPrice = from.minPrice,
        openPrice = from.openPrice,
        percentageChange = from.percentageChange,
        riseLowTypeId = from.riseLowTypeId,
        valueChange = from.valueChange
    )
}