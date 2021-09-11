package net.portes.shared.extensions

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class FloatExtensionsTest {

    @Test
    fun `validation parse money` () {
        val result = 5_000.33450f.parseMoney()
        assertEquals("$5,000.33", result)
    }

    @Test
    fun `validation parse percentage` () {
        val result = 5_000.33450f.parsePercentage()
        assertEquals("5000.33%", result)
    }

}