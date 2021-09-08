package net.portes.ipc.data.mapper

import net.portes.ipc.MockFactory.responseList
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class IpcListMapperTest {

    private val mapper by lazy {
        IpcListMapper(IpcMapper())
    }

    @Test
    fun `verify transform`() {
        with(mapper.mapFrom(responseList)) {
            assertEquals(first().change, responseList.first().change)
            assertEquals(first().percentageChange, responseList.first().percentageChange)
            assertEquals(first().price, responseList.first().price)
            assertEquals(first().volume, responseList.first().volume)
        }
    }

}