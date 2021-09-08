package net.portes.shared.domain.usecase

import net.portes.shared.models.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import net.portes.shared.models.Either

/**
 * @author amadeus.portes
 */
abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    protected abstract suspend fun execute(params: P): Either<Failure, R>

    suspend operator fun invoke(parameters: P): Either<Failure, R> {
        return withContext(coroutineDispatcher) {
            execute(parameters)
        }
    }
}