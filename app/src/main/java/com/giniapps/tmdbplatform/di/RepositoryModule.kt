package com.giniapps.tmdbplatform.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.giniapps.tmdbplatform.networking.RemoteApi
import com.giniapps.tmdbplatform.networking.RemoteApiImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRemoteApi(remoteApi: RemoteApiImpl): RemoteApi
}