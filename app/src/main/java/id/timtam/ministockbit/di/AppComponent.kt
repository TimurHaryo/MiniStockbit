package id.timtam.ministockbit.di

import id.timtam.ministockbit.di.data.dataSourceComponent
import id.timtam.ministockbit.di.data.mapperComponent
import id.timtam.ministockbit.di.data.repositoryComponent
import id.timtam.ministockbit.di.data.useCaseComponent
import id.timtam.ministockbit.di.network.networkDependency
import id.timtam.ministockbit.di.view.viewModelComponent

val appComponent = listOf(
    networkDependency,
    dataSourceComponent,
    mapperComponent,
    repositoryComponent,
    useCaseComponent,
    viewModelComponent
)