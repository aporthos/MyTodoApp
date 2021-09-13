package net.portes.ipc.data.datasource

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await
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
    private val mapper: IpcListMapper,
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : IpcDataSource {

    override fun getIpc(): Either<Failure, List<IpcDto>> =
        when (networkHandler.isNetworkAvailable()) {
            true -> service.getIpc().call({ mapper.mapFrom(it) }, mapper.toDefault())
            false -> Either.Left(Failure.NetworkConnection)
        }

    override suspend fun getIpcUrl(): Either<Failure, Boolean> =
        when (networkHandler.isNetworkAvailable()) {
            true -> getRemoteConfigIpcUrl()
            false -> Either.Left(Failure.NetworkConnection)
        }

    private suspend fun getRemoteConfigIpcUrl(): Either<Failure, Boolean> {
        val result = firebaseRemoteConfig.fetchAndActivate().await()
        return if (result) Either.Right(true) else Either.Left(Failure.ServerError)
    }
}