package id.timtam.ministockbit.domain

import id.timtam.core.exception.Failure
import id.timtam.core.vo.Either
import id.timtam.ministockbit.data.repository.TopTierVolumeRepositoryImpl
import id.timtam.ministockbit.domain.model.TopTierVolume
import id.timtam.ministockbit.domain.usecase.GetTopTierVolumeUseCase
import id.timtam.ministockbit.helper.Faker
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class GetTopTierUseCaseTest : ShouldSpec({

    val faker = Faker
    val repository: TopTierVolumeRepositoryImpl = mockk()
    lateinit var useCase: GetTopTierVolumeUseCase

    beforeTest {
        useCase = spyk(GetTopTierVolumeUseCase(repository))
    }

    afterTest {
        unmockkAll()
    }

    context("get top tier volume") {
        val fakePage = faker.int
        val fakeLimit = faker.int
        val fakeTsym = faker.string
        val query: Map<String, String> = mapOf(
            "page" to fakePage.toString(),
            "limit" to fakeLimit.toString(),
            "tsym" to fakeTsym
        )
        val param = GetTopTierVolumeUseCase.Query(
            page = fakePage,
            limit = fakeLimit,
            tsym = fakeTsym
        )

        should("success get top tier volume through use case") {
            // Given
            val fakeResult: Either<Nothing, List<TopTierVolume>> = mockk()

            coEvery { repository.getTotalTopTier(query) } returns fakeResult

            // When
            val result = useCase.run(param)

            // Then
            result shouldBe fakeResult
            coVerify { repository.getTotalTopTier(query) }
        }

        should("error get top tier volume through use case") {
            // Given
            val fakeResult: Either<Failure, Nothing> = mockk()

            coEvery { repository.getTotalTopTier(query) } returns fakeResult

            // When
            val result = useCase.run(param)

            // Then
            result shouldBe fakeResult
        }
    }

})