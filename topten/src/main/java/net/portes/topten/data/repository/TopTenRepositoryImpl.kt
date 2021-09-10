package net.portes.topten.data.repository

import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.topten.domain.datasource.TopTenDataSource
import net.portes.topten.domain.models.TopTenItem
import net.portes.topten.domain.repository.TopTenRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Singleton
class TopTenRepositoryImpl @Inject constructor(private val dataSource: TopTenDataSource) : TopTenRepository {

    override fun getTopTen(): Either<Failure, List<TopTenItem>> = dataSource.getTopTen()

}