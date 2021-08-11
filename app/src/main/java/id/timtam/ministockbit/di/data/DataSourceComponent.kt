package id.timtam.ministockbit.di.data

import id.timtam.ministockbit.data.remote.source.RemoteDataSource
import org.koin.dsl.module

val dataSourceComponent = module {
    factory { RemoteDataSource(get()) }
}