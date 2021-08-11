package id.timtam.ministockbit.di.dispatcher

import id.timtam.core.dispatcher.DispatcherProviderImpl
import org.koin.dsl.module

val dispatcherComponent = module {
    factory { DispatcherProviderImpl() }
}