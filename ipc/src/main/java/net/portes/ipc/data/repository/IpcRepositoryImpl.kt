package net.portes.ipc.data.repository

import net.portes.shared.models.Failure
import net.portes.ipc.domain.datasource.IpcDataSource
import net.portes.ipc.domain.models.IpcDto
import net.portes.ipc.domain.repository.IpcRepository
import net.portes.shared.models.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Singleton
class IpcRepositoryImpl @Inject constructor(private val dataSource: IpcDataSource) : IpcRepository {

    override fun getIpc(): Either<Failure, List<IpcDto>> = dataSource.getIpc()

}