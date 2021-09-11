package net.portes.login.domain.repository

import net.portes.shared.models.Either
import net.portes.shared.models.Failure

/**
 * @author amadeus.portes
 */
interface LoginFirebaseRepository {
    suspend fun login(): Either<Failure, Boolean>
}