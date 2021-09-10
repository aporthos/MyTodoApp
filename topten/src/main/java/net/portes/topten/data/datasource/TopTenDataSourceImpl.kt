package net.portes.topten.data.datasource

import net.portes.shared.NetworkHandler
import net.portes.shared.extensions.call
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.topten.data.mapper.TopTenListMapper
import net.portes.topten.data.services.TopTenService
import net.portes.topten.domain.datasource.TopTenDataSource
import net.portes.topten.domain.models.TopTenItem
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Singleton
class TopTenDataSourceImpl @Inject constructor(
    private val service: TopTenService,
    private val networkHandler: NetworkHandler,
    private val mapper: TopTenListMapper
) : TopTenDataSource {

    override fun getTopTen(): Either<Failure, List<TopTenItem>> =
        when (networkHandler.isNetworkAvailable()) {
            true -> service.getTopTen().call({ mapper.mapFrom(it) }, mapper.toDefault())
            false -> Either.Left(Failure.NetworkConnection)
        }

}