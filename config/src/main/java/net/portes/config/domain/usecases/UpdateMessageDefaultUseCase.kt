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
class UpdateMessageDefaultUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val preferences: ConfigSharedPref
) : UseCase<String, Unit>(dispatcher) {
    override suspend fun execute(params: String): Either<Failure, Unit> {
        preferences.messageShareDefault = params
        return Either.Right(Unit)
    }
}