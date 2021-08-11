package id.timtam.core.exception

data class Failure(
    val requestsResult: Int,
    val throwable: Throwable
)
