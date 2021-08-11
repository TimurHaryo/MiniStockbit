package id.timtam.ministockbit.data.repository

import id.timtam.core.exception.Failure
import id.timtam.core.exception.RequestsResult
import id.timtam.core.vo.Either
import id.timtam.ministockbit.data.remote.mapper.TopTierMapper
import id.timtam.ministockbit.data.remote.response.TotalTopTierResponse
import id.timtam.ministockbit.data.remote.source.RemoteDataSource
import id.timtam.ministockbit.domain.model.TopTierVolume
import id.timtam.ministockbit.domain.repository.TopTierVolumeRepository
import id.timtam.ministockbit.helper.Faker
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class TopTierRepositoryTest : ShouldSpec({

    val faker = Faker
    val remoteDataSource: RemoteDataSource = mockk()
    val topTierMapper: TopTierMapper = mockk()
    lateinit var topTierRepository: TopTierVolumeRepository

    beforeTest {
        topTierRepository = spyk(
            TopTierVolumeRepositoryImpl(
                remoteDataSource = remoteDataSource,
                topTierMapper = topTierMapper
            )
        )
    }

    afterTest {
        unmockkAll()
    }

    context("get top tier from data source") {
        val fakePage = faker.int
        val fakeLimit = faker.int
        val query: Map<String, String> = mapOf(
            "page" to fakePage.toString(),
            "limit" to fakeLimit.toString(),
            "tsym" to "USD"
        )

        should("success get top tier scenario to domain model") {
            // Given
            val fakeResult: List<TopTierVolume> = mockk()
            val fakeResponse: TotalTopTierResponse = mockk()

            every { topTierMapper.map(any()) } returns fakeResult
            coEvery { remoteDataSource.getTotalTopTier(query) } returns Either.Success(fakeResponse)

            // When
            val result = topTierRepository.getTotalTopTier(query)

            // Then
            result shouldBe Either.Success(fakeResult)
            coVerify { remoteDataSource.getTotalTopTier(query) }
            verify { topTierMapper.map(fakeResponse) }
        }

        should("error get top tier scenario to domain model") {
            // Given
            val fakeMessage = faker.string
            val fakeThrowable = Throwable(fakeMessage)
            mockkObject(RequestsResult.SERVER_ERROR)
            val fakeFailure = Failure(RequestsResult.SERVER_ERROR, fakeThrowable)

            coEvery { remoteDataSource.getTotalTopTier(query) } returns Either.Error(fakeFailure)

            // When
            val result = topTierRepository.getTotalTopTier(query)

            // Then
            result shouldBe Either.Error(fakeFailure)
        }
    }

})