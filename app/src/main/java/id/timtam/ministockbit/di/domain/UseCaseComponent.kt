package id.timtam.ministockbit.di.domain

import id.timtam.ministockbit.domain.usecase.GetTopTierVolumeUseCase
import org.koin.dsl.module

val useCaseComponent = module {
    factory { GetTopTierVolumeUseCase(get()) }
}