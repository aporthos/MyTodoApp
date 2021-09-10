package net.portes.ipc.data.datasource

import net.portes.shared.models.Failure
import net.portes.ipc.data.mapper.IpcListMapper
import net.portes.ipc.data.services.IpcService
import net.portes.ipc.domain.datasource.IpcDataSource
import net.portes.ipc.domain.models.IpcDto
import net.portes.shared.NetworkHandler
import net.portes.shared.extensions.call
import net.portes.shared.models.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Singleton
class IpcDataSourceImpl @Inject constructor(
    private val service: IpcService,
    private val networkHandler: NetworkHandler,
    private val mapper: IpcListMapper
) : IpcDataSource {

    override fun getIpc(): Either<Failure, List<IpcDto>> =
        when (networkHandler.isNetworkAvailable()) {
            true -> service.getIpc().call({ mapper.mapFrom(it) }, mapper.toDefault())
            false -> Either.Left(Failure.NetworkConnection)
        }

}