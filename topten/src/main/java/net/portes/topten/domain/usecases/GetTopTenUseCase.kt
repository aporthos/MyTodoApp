package net.portes.topten.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import net.portes.shared.di.IoDispatcher
import net.portes.shared.domain.usecase.UseCase
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.topten.domain.models.TopTenItem
import net.portes.topten.domain.repository.TopTenRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Singleton
class GetTopTenUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: TopTenRepository
): UseCase<Unit, List<TopTenItem>>(dispatcher) {

    override suspend fun execute(params: Unit): Either<Failure, List<TopTenItem>> = repository.getTopTen()

}