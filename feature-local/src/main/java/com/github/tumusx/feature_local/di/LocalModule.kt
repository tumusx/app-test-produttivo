package com.github.tumusx.feature_local.di

import com.github.tumusx.core_database.database.AppDatabase
import com.github.tumusx.feature_local.data.LocalRepositoryImpl
import com.github.tumusx.feature_local.domain.ILocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideRepository(appDatabase: AppDatabase): ILocalRepository {
        return LocalRepositoryImpl(appDatabase)
    }

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}