package id.timtam.ministockbit.domain.usecase

import id.timtam.core.abstraction.UseCase
import id.timtam.core.exception.Failure
import id.timtam.core.vo.Either
import id.timtam.ministockbit.domain.model.TopTierVolume
import id.timtam.ministockbit.domain.repository.TopTierVolumeRepository

class GetTopTierVolumeUseCase(
    private val repository: TopTierVolumeRepository
) : UseCase<List<TopTierVolume>, GetTopTierVolumeUseCase.Query>() {

    data class Query(
        val page: Int,
        val limit: Int,
        val tsym: String
    )

    override suspend fun run(param: Query): Either<Failure, List<TopTierVolume>> {
        val queries = HashMap<String, String>()

        queries.apply {
            put("page", param.page.toString())
            put("limit", param.limit.toString())
            put("tsym", param.tsym)
        }

        return repository.getTotalTopTier(queries)
    }
}