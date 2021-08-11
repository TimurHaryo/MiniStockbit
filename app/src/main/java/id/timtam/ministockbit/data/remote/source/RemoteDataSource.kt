package id.timtam.ministockbit.data.remote.source

import id.timtam.core.api.SafeApiRequest
import id.timtam.core.exception.Failure
import id.timtam.core.vo.Either
import id.timtam.ministockbit.data.remote.response.TotalTopTierResponse
import id.timtam.ministockbit.data.remote.service.ApiService

class RemoteDataSource(private val api: ApiService) : SafeApiRequest() {
    suspend fun getTotalTopTier(queries: Map<String, String>): Either<Failure, TotalTopTierResponse> =
        request {
            api.getTotalTopTier(queries)
        }
}