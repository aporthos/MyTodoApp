package net.portes.login.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runBlockingTest
import net.portes.shared.NetworkHandler
import net.portes.shared.models.Either
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class LoginFirebaseDataSourceImplTest {

    @Mock
    private lateinit var networkHandler: NetworkHandler

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Mock
    private lateinit var result: Task<AuthResult>

    @Mock
    private lateinit var task: AuthResult

    @Mock
    private lateinit var firebaseUser: FirebaseUser

    private val dataSource by lazy {
        LoginFirebaseDataSourceImpl(networkHandler, firebaseAuth)
    }

    @Test
    fun `validate data source login success`() = runBlockingTest {
        // Given
        `when`(networkHandler.isNetworkAvailable()).thenReturn(true)
        `when`(task.user).thenReturn(firebaseUser)
        `when`(result.isComplete).thenReturn(true)
        `when`(result.await()).thenReturn(task)
        `when`(firebaseAuth.signInAnonymously()).thenReturn(result)

        // When
        val data = dataSource.login()

        //Then
        assertEquals(true, data is Either.Right)
    }

    @Test
    fun `validate data source login failed`() = runBlockingTest {
        // Given
        `when`(networkHandler.isNetworkAvailable()).thenReturn(true)
        `when`(task.user).thenReturn(null)
        `when`(result.isComplete).thenReturn(true)
        `when`(result.await()).thenReturn(task)
        `when`(firebaseAuth.signInAnonymously()).thenReturn(result)

        // When
        val data = dataSource.login()

        //Then
        assertEquals(true, data is Either.Left)
    }

    @Test
    fun `validate data source login is network not available`() = runBlockingTest {
        // Given
        `when`(networkHandler.isNetworkAvailable()).thenReturn(false)

        // When
        val data = dataSource.login()

        //Then
        assertEquals(true, data is Either.Left)
    }

}