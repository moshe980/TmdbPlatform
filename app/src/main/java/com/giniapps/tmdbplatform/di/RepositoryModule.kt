package com.giniapps.tmdbplatform.di

import com.giniapps.tmdbplatform.networking.RemoteApi
import com.giniapps.tmdbplatform.networking.RemoteApiImpl
import com.giniapps.tmdbplatform.repository.TmdbRepository
import com.giniapps.tmdbplatform.repository.TmdbRepositoryLogic
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repository: TmdbRepositoryLogic): TmdbRepository
}