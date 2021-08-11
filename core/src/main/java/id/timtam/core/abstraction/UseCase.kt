package id.timtam.core.abstraction

import id.timtam.core.exception.Failure
import id.timtam.core.vo.Either

abstract class UseCase<out Type, in Param> where Type : Any {

    abstract suspend fun run(param: Param) : Either<Failure, Type>

    object None

}