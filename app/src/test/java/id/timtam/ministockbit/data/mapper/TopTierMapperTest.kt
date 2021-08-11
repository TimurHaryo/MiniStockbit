package id.timtam.ministockbit.data.mapper

import id.timtam.ministockbit.data.remote.mapper.TopTierMapper
import id.timtam.ministockbit.data.remote.response.TotalTopTierResponse
import id.timtam.ministockbit.domain.model.TopTierVolume
import id.timtam.ministockbit.helper.Faker
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.spyk
import io.mockk.unmockkAll

class TopTierMapperTest : ShouldSpec({

    val faker = Faker
    lateinit var mapper: TopTierMapper

    beforeTest {
        mapper = spyk(TopTierMapper())
    }

    afterTest {
        unmockkAll()
    }

    context("map raw to domain") {
        should("map top tier response to list of top tier domain model") {
            // Given
            val fakeResponse = TotalTopTierResponse(
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

            // When
            val result = mapper.map(fakeResponse)

            // Then
            result shouldBe instanceOf<List<TopTierVolume>>()
        }
    }

})