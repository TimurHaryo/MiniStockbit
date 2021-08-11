package id.timtam.ministockbit.domain.repository

import id.timtam.core.exception.Failure
import id.timtam.core.vo.Either
import id.timtam.ministockbit.domain.model.TopTierVolume

interface TopTierVolumeRepository {
    suspend fun getTotalTopTier(queries: Map<String, String>): Either<Failure, List<TopTierVolume>>
}