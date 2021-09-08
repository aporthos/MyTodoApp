package net.portes.ipc.domain.usecases

import net.portes.shared.models.Failure
import kotlinx.coroutines.CoroutineDispatcher
import net.portes.ipc.domain.model.IpcDto
import net.portes.ipc.domain.repository.IpcRepository
import net.portes.shared.domain.usecase.UseCase
import net.portes.shared.models.Either
import net.portes.shared.di.IoDispatcher
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Singleton
class GetIpcUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: IpcRepository
): UseCase<Unit, List<IpcDto>>(dispatcher) {

    override suspend fun execute(params: Unit): Either<Failure, List<IpcDto>> = repository.getIpc()

}