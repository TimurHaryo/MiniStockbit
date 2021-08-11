package id.timtam.core.api

import id.timtam.core.exception.Failure
import id.timtam.core.exception.RequestsResult
import id.timtam.core.extension.hasEmptyBody
import id.timtam.core.extension.isTotallySuccess
import id.timtam.core.vo.Either
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class SafeApiRequest : ApiRequest {

    override suspend fun <T> request(apiCall: suspend () -> Response<T>): Either<Failure, T> {
        return try {
            val response = apiCall.invoke()

            when {
                response.isTotallySuccess() -> Either.Success(response.body()!!)
                response.hasEmptyBody() -> parseError(
                    Failure((RequestsResult.DATA_NOT_MATCH), Throwable("Empty Body"))
                )
                response.code() in 300..599 -> parseError(
                    Failure(RequestsResult.SERVER_ERROR, Throwable("\"[${response.code()}] - [${response.message()}]\""))
                )
                else -> parseError(
                    Failure(RequestsResult.UNKNOWN_ERROR, Throwable("Unknown error from server"))
                )
            }
        } catch (throwable: Throwable) {
            when(throwable) {
                is UnknownHostException -> parseError(
                    Failure(RequestsResult.SERVER_ERROR,Throwable("Network problem"))
                )
                is ConnectException -> parseError(
                    Failure(RequestsResult.SERVER_ERROR,throwable)
                )
                is SocketTimeoutException -> parseError(
                    Failure(RequestsResult.TIMEOUT,throwable)
                )
                else -> parseError(
                    Failure(RequestsResult.UNKNOWN_ERROR,throwable)
                )
            }
        }
    }

    private fun parseError(failure: Failure) : Either.Error<Failure> = Either.Error(failure)
}