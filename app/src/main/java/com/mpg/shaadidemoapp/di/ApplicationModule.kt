package com.mpg.shaadidemoapp.di

import com.mpg.shaadidemoapp.data.local.LocalRepository
import com.mpg.shaadidemoapp.data.Repository
import com.mpg.shaadidemoapp.data.RepositoryImpl
import com.mpg.shaadidemoapp.data.local.InventoryDao
import com.mpg.shaadidemoapp.data.local.LocalRepositoryImpl
import com.mpg.shaadidemoapp.data.remote.RemoteRepository
import com.mpg.shaadidemoapp.data.remote.RemoteRepositoryImpl
import com.mpg.shaadidemoapp.data.remote.UserApi
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
//    @Binds
//    abstract fun getRepository(inventoryDataSource: RepositoryImpl): Repository
//
//    @Binds
//    abstract fun getLocalRepository(inventoryDao: LocalInventoryRepository): InventoryDataSource

    @Provides
    internal fun getRepository(
        localRepository: LocalRepository,
        remoteRepository: RemoteRepository
    ): Repository {
        return RepositoryImpl(localRepository, remoteRepository)
    }

    @Provides
    internal fun getLocalRepository(inventoryDao: InventoryDao): LocalRepository {
        return LocalRepositoryImpl(inventoryDao)
    }

    @Provides
    internal fun getRemoteRepository(userApi: UserApi): RemoteRepository {
        return RemoteRepositoryImpl(userApi)
    }

}