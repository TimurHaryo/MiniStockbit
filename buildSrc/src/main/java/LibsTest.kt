internal object LibsTestVersion {
    const val mockk = "1.12.0"
    const val kotest = "4.6.1"
}

object LibsTest {
    const val mockk = "io.mockk:mockk:${LibsTestVersion.mockk}"
    const val kotestRunner = "io.kotest:kotest-runner-junit5:${LibsTestVersion.kotest}"
    const val kotestAssertions = "io.kotest:kotest-assertions-core:${LibsTestVersion.kotest}"
    const val kotestProperty = "io.kotest:kotest-property:${LibsTestVersion.kotest}"
}