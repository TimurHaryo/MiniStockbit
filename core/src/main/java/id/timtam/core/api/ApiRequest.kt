package id.timtam.core.api

import id.timtam.core.exception.Failure
import id.timtam.core.vo.Either
import retrofit2.Response

interface ApiRequest {
    suspend fun <T> request(apiCall: suspend () -> Response<T>) : Either<Failure, T>
}