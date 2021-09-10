package net.portes.topten.data.mapper

import net.portes.topten.MockFactory.responseList
import net.portes.topten.MockFactory.responseUps
import net.portes.topten.MockFactory.responseVolume
import net.portes.topten.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class TopTenListMapperTest {

    private val mapper by lazy {
        TopTenListMapper(TopTenMapper())
    }

    @Test
    fun `verify transform success categories all`() {

        with(mapper.mapFrom(responseList)) {
            assertEquals(size, 3)
            assertEquals(get(0).header.name, R.string.title_topten_type_ups)
            assertEquals(get(1).header.name, R.string.title_topten_type_downs)
            assertEquals(get(2).header.name, R.string.title_topten_type_volume)

            assertEquals(get(0).items.size, 1)
            assertEquals(get(1).items.size, 1)
            assertEquals(get(2).items.size, 1)

            assertEquals(get(0).items.first().issueId, responseList[1].issueId)
            assertEquals(get(0).items.first().benchmarkId, responseList[1].benchmarkId)
            assertEquals(get(0).items.first().aggregatedVolume, responseList[1].aggregatedVolume)
            assertEquals(get(0).items.first().askPrice, responseList[1].askPrice)
            assertEquals(get(0).items.first().askVolume, responseList[1].askVolume)
            assertEquals(
                get(0).items.first().benchmarkPercentage,
                responseList[1].benchmarkPercentage
            )
            assertEquals(get(0).items.first().bidPrice, responseList[1].bidPrice)
            assertEquals(get(0).items.first().bidVolume, responseList[1].bidVolume)
            assertEquals(get(0).items.first().closePrice, responseList[1].closePrice)
            assertEquals(get(0).items.first().instrumentTypeId, responseList[1].instrumentTypeId)
            assertEquals(
                get(0).items.first().ipcParticipationRate,
                responseList[1].ipcParticipationRate
            )
            assertEquals(get(0).items.first().lastPrice, responseList[1].lastPrice)
            assertEquals(get(0).items.first().maxPrice, responseList[1].maxPrice)
            assertEquals(get(0).items.first().minPrice, responseList[1].minPrice)
            assertEquals(get(0).items.first().percentageChange, responseList[1].percentageChange)
            assertEquals(get(0).items.first().valueChange, responseList[1].valueChange)
        }
    }

    @Test
    fun `verify transform success categories only two`() {
        val response = mutableListOf(
            responseUps(),
            responseVolume()
        )
        with(mapper.mapFrom(response)) {
            assertEquals(size, 2)
            assertEquals(get(0).items.size, 1)
            assertEquals(get(1).items.size, 1)

            assertEquals(get(0).header.name, R.string.title_topten_type_ups)
            assertEquals(get(1).header.name, R.string.title_topten_type_volume)

            assertEquals(get(0).items.first().issueId, response[0].issueId)
            assertEquals(get(0).items.first().benchmarkId, response[0].benchmarkId)
            assertEquals(get(0).items.first().aggregatedVolume, response[0].aggregatedVolume)
            assertEquals(get(0).items.first().askPrice, response[0].askPrice)
            assertEquals(get(0).items.first().askVolume, response[0].askVolume)
            assertEquals(get(0).items.first().benchmarkPercentage, response[0].benchmarkPercentage)
            assertEquals(get(0).items.first().bidPrice, response[0].bidPrice)
            assertEquals(get(0).items.first().bidVolume, response[0].bidVolume)
            assertEquals(get(0).items.first().closePrice, response[0].closePrice)
            assertEquals(get(0).items.first().instrumentTypeId, response[0].instrumentTypeId)
            assertEquals(
                get(0).items.first().ipcParticipationRate,
                response[0].ipcParticipationRate
            )
            assertEquals(get(0).items.first().lastPrice, response[0].lastPrice)
            assertEquals(get(0).items.first().maxPrice, response[0].maxPrice)
            assertEquals(get(0).items.first().minPrice, response[0].minPrice)
            assertEquals(get(0).items.first().percentageChange, response[0].percentageChange)
            assertEquals(get(0).items.first().valueChange, response[0].valueChange)
        }
    }

}