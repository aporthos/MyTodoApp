package net.portes.ipc.data.mapper

import net.portes.ipc.MockFactory.response
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class IpcMapperTest {

    private val mapper by lazy {
        IpcMapper()
    }

    @Test
    fun `verify transform`() {
        with(mapper.mapFrom(response())) {
            assertEquals(change, response().change)
            assertEquals(percentageChange, response().percentageChange)
            assertEquals(price, response().price)
            assertEquals(volume, response().volume)
        }
    }

}