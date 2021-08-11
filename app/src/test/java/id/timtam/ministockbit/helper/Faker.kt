package id.timtam.ministockbit.helper

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.next
import io.kotest.property.arbitrary.string

interface IFaker {
    val string: String
    val int: Int
}

object Faker : IFaker {
    override val string: String
        get() = Arb.string().next()
    override val int: Int
        get() = Arb.int().next()
}