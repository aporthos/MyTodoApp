package net.portes.login.data.repository

import net.portes.login.domain.datasource.LoginFirebaseDataSource
import net.portes.login.domain.repository.LoginFirebaseRepository
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Singleton
class LoginFirebaseRepositoryImpl @Inject constructor(private val dataSource: LoginFirebaseDataSource) :
    LoginFirebaseRepository {

    override suspend fun login(): Either<Failure, Boolean> = dataSource.login()

}