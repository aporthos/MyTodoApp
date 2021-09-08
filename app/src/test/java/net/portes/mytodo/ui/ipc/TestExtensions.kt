package net.portes.mytodo.ui.ipc

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

/**
 * @author amadeus.portes
 */
@ExperimentalCoroutinesApi
fun MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) = testDispatcher.runBlockingTest {
    block()
}