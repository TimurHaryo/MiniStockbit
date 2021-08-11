package id.timtam.ministockbit.di.data

import id.timtam.ministockbit.data.repository.TopTierVolumeRepositoryImpl
import id.timtam.ministockbit.domain.repository.TopTierVolumeRepository
import org.koin.dsl.module

val repositoryComponent = module {
    factory<TopTierVolumeRepository> { TopTierVolumeRepositoryImpl(get(), get()) }
}