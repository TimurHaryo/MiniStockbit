package id.timtam.core.vo

sealed class Either<out Failed, out Result> {
    data class Success<out R>(val result: R) : Either<Nothing, R>()
    data class Error<out L>(val failure: L) : Either<L, Nothing>()
}
