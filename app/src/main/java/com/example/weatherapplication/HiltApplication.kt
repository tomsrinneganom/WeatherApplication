package com.example.weatherapplication

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapplication.database.localdatabase.AppDatabase
import com.example.weatherapplication.database.localdatabase.RoomDatabaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@HiltAndroidApp
class HiltApplication : Application() {

    @Module
    @InstallIn(SingletonComponent::class)
    object RoomModule {
        @Provides
        fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(context,
                AppDatabase::class.java,
                RoomDatabaseProvider.DATABASE_NAME).build()
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object RoomDatabaseProviderModule {
        @Provides
        fun provideRoomDatabaseProvider(database: AppDatabase): RoomDatabaseProvider {
            return RoomDatabaseProvider(database)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object RepositoryModule {

        @Provides
        fun provideRepository(databaseProvider: RoomDatabaseProvider): Repository {
            return Repository(databaseProvider)
        }
    }

//    @Module
//    @InstallIn(SingletonComponent::class)
//    object DataStoreModule{
//        @Provides
//        fun provideDataStore(@ApplicationContext context: Context){
//        }
//    }

}