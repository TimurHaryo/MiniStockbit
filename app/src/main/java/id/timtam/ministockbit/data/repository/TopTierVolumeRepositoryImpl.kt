package id.timtam.ministockbit.data.repository

import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import id.timtam.core.exception.Failure
import id.timtam.core.vo.Either
import id.timtam.ministockbit.data.remote.mapper.TopTierMapper
import id.timtam.ministockbit.data.remote.source.RemoteDataSource
import id.timtam.ministockbit.domain.model.TopTierVolume
import id.timtam.ministockbit.domain.repository.TopTierVolumeRepository

class TopTierVolumeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val topTierMapper: TopTierMapper
): TopTierVolumeRepository {

    override suspend fun getTotalTopTier(queries: Map<String, String>): Either<Failure, List<TopTierVolume>> {
        return when(val result = remoteDataSource.getTotalTopTier(queries)) {
            is Either.Success -> {
                d { "success get top tier volume" }
                Either.Success(topTierMapper.map(result.result))
            }
            is Either.Error -> {
                e { "error get top tier volume" }
                Either.Error(result.failure)
            }
        }
    }

}