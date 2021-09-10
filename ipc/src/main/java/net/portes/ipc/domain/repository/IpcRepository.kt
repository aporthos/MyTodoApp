package net.portes.ipc.domain.repository

import net.portes.shared.models.Failure
import net.portes.ipc.domain.models.IpcDto
import net.portes.shared.models.Either

/**
 * @author amadeus.portes
 */
interface IpcRepository {
    fun getIpc() : Either<Failure, List<IpcDto>>
}