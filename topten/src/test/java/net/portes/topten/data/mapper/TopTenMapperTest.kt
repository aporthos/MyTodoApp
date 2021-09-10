package net.portes.topten.data.mapper

import net.portes.topten.MockFactory.responseDowns
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class TopTenMapperTest {

    private val mapper by lazy {
        TopTenMapper()
    }

    @Test
    fun `verify transform`() {
        with(mapper.mapFrom(responseDowns())) {
            assertEquals(issueId, responseDowns().issueId)
            assertEquals(benchmarkId, responseDowns().benchmarkId)
            assertEquals(aggregatedVolume, responseDowns().aggregatedVolume)
            assertEquals(askPrice, responseDowns().askPrice)
            assertEquals(askVolume, responseDowns().askVolume)
            assertEquals(benchmarkPercentage, responseDowns().benchmarkPercentage)
            assertEquals(bidPrice, responseDowns().bidPrice)
            assertEquals(bidVolume, responseDowns().bidVolume)
            assertEquals(closePrice, responseDowns().closePrice)
            assertEquals(instrumentTypeId, responseDowns().instrumentTypeId)
            assertEquals(ipcParticipationRate, responseDowns().ipcParticipationRate)
            assertEquals(lastPrice, responseDowns().lastPrice)
            assertEquals(maxPrice, responseDowns().maxPrice)
            assertEquals(minPrice, responseDowns().minPrice)
            assertEquals(percentageChange, responseDowns().percentageChange)
            assertEquals(valueChange, responseDowns().valueChange)
        }
    }

}