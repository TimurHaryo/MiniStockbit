package id.timtam.ministockbit.di.data

import id.timtam.ministockbit.data.remote.mapper.TopTierMapper
import org.koin.dsl.module

val mapperComponent = module {
    factory { TopTierMapper() }
}