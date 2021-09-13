package net.portes.config.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import net.portes.config.prefs.ConfigSharedPref
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
class GetMessageDefaultUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val preferences: ConfigSharedPref
) : UseCase<Unit, String>(dispatcher) {

    override suspend fun execute(params: Unit): Either<Failure, String> =
        Either.Right(preferences.messageShareDefault)

}