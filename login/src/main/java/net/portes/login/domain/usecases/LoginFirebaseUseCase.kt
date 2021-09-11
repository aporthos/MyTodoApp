package net.portes.login.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import net.portes.login.domain.repository.LoginFirebaseRepository
import net.portes.shared.di.IoDispatcher
import net.portes.shared.domain.usecase.UseCase
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Singleton
class LoginFirebaseUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: LoginFirebaseRepository
): UseCase<Unit, Boolean>(dispatcher) {

    override suspend fun execute(params: Unit): Either<Failure, Boolean> = repository.login()

}