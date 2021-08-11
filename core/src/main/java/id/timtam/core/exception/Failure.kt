package id.timtam.core.exception

data class Failure(
    val requestsResult: RequestsResult,
    val throwable: Throwable
)
