package net.portes.ipc.domain.datasource

import net.portes.shared.models.Failure
import net.portes.ipc.domain.models.IpcDto
import net.portes.shared.models.Either

/**
 * @author amadeus.portes
 */
interface IpcDataSource {
    fun getIpc() : Either<Failure, List<IpcDto>>
}