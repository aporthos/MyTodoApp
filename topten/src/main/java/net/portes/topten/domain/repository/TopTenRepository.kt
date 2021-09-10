package net.portes.topten.domain.repository

import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.topten.domain.models.TopTenItem

/**
 * @author amadeus.portes
 */
interface TopTenRepository {
    fun getTopTen(): Either<Failure, List<TopTenItem>>
}