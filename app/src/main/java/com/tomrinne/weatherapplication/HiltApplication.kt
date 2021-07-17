package com.tomrinne.weatherapplication

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tomrinne.weatherapplication.database.Database
import com.tomrinne.weatherapplication.database.IDatabase
import com.tomrinne.weatherapplication.database.Repository
import com.tomrinne.weatherapplication.database.localdatabase.AppDatabase
import com.tomrinne.weatherapplication.database.localdatabase.ILocalDatabaseProvider
import com.tomrinne.weatherapplication.database.localdatabase.RoomDatabaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

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

        @Provides
        fun provideRoomDatabaseProvider(database: AppDatabase): ILocalDatabaseProvider {
            return RoomDatabaseProvider(database)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object RepositoryModule {

        @Provides
        fun provideRepository(
            localDatabaseProvider: ILocalDatabaseProvider,
            database: IDatabase,
        ): Repository {
            return Repository(localDatabaseProvider, database)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DatabaseModule {

        @Provides
        fun provideDatabase(): IDatabase {
            return Database()
        }
    }


}