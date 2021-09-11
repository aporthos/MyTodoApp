package net.portes.login.data.datasource

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import net.portes.login.domain.datasource.LoginFirebaseDataSource
import net.portes.shared.NetworkHandler
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Singleton
class LoginFirebaseDataSourceImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val firebaseAuth: FirebaseAuth
) : LoginFirebaseDataSource {
    override suspend fun login(): Either<Failure, Boolean> =
        when (networkHandler.isNetworkAvailable()) {
            true -> loginFirebase()
            false -> Either.Left(Failure.NetworkConnection)
        }

    private suspend fun loginFirebase(): Either<Failure, Boolean> {
        val result = firebaseAuth.signInAnonymously().await()
        return result.user?.let {
            Either.Right(true)
        } ?: run {
            Either.Left(Failure.ServerError)
        }
    }

}
