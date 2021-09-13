package net.portes.config.domain.usecases

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import net.portes.config.prefs.ConfigSharedPref
import net.portes.shared.models.Either
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class GetMessageDefaultUseCaseTest {

    @Mock
    private lateinit var preferences: ConfigSharedPref

    private val useCase by lazy {
        GetMessageDefaultUseCase(TestCoroutineDispatcher(), preferences)
    }

    @Test
    fun `validation use case success`() = runBlockingTest {
        val loginUseCase = useCase(Unit)
        assertEquals(true, loginUseCase is Either.Right)
    }

}