package net.portes.login.domain.datasource

import net.portes.shared.models.Either
import net.portes.shared.models.Failure

/**
 * @author amadeus.portes
 */
interface LoginFirebaseDataSource {
    suspend fun login(): Either<Failure, Boolean>
}