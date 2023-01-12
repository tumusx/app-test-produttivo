package com.github.tumusx.feature_equipment.di

import android.app.Application
import androidx.room.Room
import com.github.tumusx.core_database.database.AppDatabase
import com.github.tumusx.feature_equipment.data.EquipmentRepositoryImpl
import com.github.tumusx.feature_equipment.domain.IEquipmentRepository
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
object EquipmentModule {

    @Provides
    @Singleton
    fun provideRepository(appDatabase: AppDatabase): IEquipmentRepository {
        return EquipmentRepositoryImpl(appDatabase)
    }

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideDbInstance(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "appDatabseUtil.db")
            .build()
    }
}