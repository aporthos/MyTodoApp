package net.portes.mytodo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.portes.ipc.data.datasource.IpcDataSourceImpl
import net.portes.ipc.data.repository.IpcRepositoryImpl
import net.portes.ipc.data.services.IpcService
import net.portes.ipc.domain.datasource.IpcDataSource
import net.portes.ipc.domain.repository.IpcRepository
import net.portes.shared.NetworkHandler
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Module
@InstallIn(ApplicationComponent::class)
object IpcModule {

    @Singleton
    @Provides
    fun providesIpcService(retrofit: Retrofit): IpcService =
        retrofit.create(IpcService::class.java)

    @Singleton
    @Provides
    fun providesIpcDataSource(ipcDataSourceImpl: IpcDataSourceImpl): IpcDataSource = ipcDataSourceImpl

    @Singleton
    @Provides
    fun providesIpcRepository(ipcRepositoryImpl: IpcRepositoryImpl): IpcRepository = ipcRepositoryImpl

}