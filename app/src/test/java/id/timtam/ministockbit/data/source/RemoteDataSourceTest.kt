package id.timtam.ministockbit.data.source

import id.timtam.core.vo.Either
import id.timtam.ministockbit.data.remote.response.TotalTopTierResponse
import id.timtam.ministockbit.data.remote.service.ApiService
import id.timtam.ministockbit.data.remote.source.RemoteDataSource
import id.timtam.ministockbit.helper.Faker
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import retrofit2.Response

class RemoteDataSourceTest : ShouldSpec({

    val faker = Faker
    val apiService: ApiService = mockk()
    lateinit var remoteDataSource: RemoteDataSource

    beforeTest {
        remoteDataSource = spyk(RemoteDataSource(apiService))
    }

    afterTest {
        unmockkAll()
    }

    context("get top tier from API") {
        val fakePage = faker.int
        val fakeLimit = faker.int
        val query: Map<String, String> = mapOf(
            "page" to fakePage.toString(),
            "limit" to fakeLimit.toString(),
            "tsym" to "USD"
        )
        val fakeResponse: Response<TotalTopTierResponse> = mockk()

        should("success calling response from API") {
            // Given
            val fakeResult = TotalTopTierResponse(
                data = listOf(
                    TotalTopTierResponse.Data(
                        coinInfo = TotalTopTierResponse.Data.CoinInfo(
                            id = faker.string,
                            name = faker.string,
                            fullName = faker.string
                        ),
                        display = TotalTopTierResponse.Data.DISPLAY(
                            usd = TotalTopTierResponse.Data.DISPLAY.USD(
                                price = faker.string
                            )
                        )
                    )
                )
            )

            every { fakeResponse.isSuccessful } returns true
            every { fakeResponse.body() } returns fakeResult
            coEvery { apiService.getTotalTopTier(query) } returns fakeResponse
            // When
            val response = remoteDataSource.getTotalTopTier(query)

            // Then
            response shouldBe Either.Success(fakeResult)
            coVerify { apiService.getTotalTopTier(query) }
        }

        should("error calling response from API") {
            // Given
            val fakeMessage = faker.string
            val fakeCode = faker.int

            every { fakeResponse.isSuccessful } returns false
            every { fakeResponse.message() } returns fakeMessage
            every { fakeResponse.code() } returns fakeCode

            // When
            val response = remoteDataSource.getTotalTopTier(query)

            // Then
            response as Either.Error
            response.failure.throwable.message shouldBe fakeMessage
            response.failure.requestsResult shouldBe fakeCode
        }
    }

})