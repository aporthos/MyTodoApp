package net.portes.mytodo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.portes.topten.data.datasource.TopTenDataSourceImpl
import net.portes.topten.data.repository.TopTenRepositoryImpl
import net.portes.topten.data.services.TopTenService
import net.portes.topten.domain.datasource.TopTenDataSource
import net.portes.topten.domain.repository.TopTenRepository
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Module
@InstallIn(ApplicationComponent::class)
object TopTenModule {

    @Singleton
    @Provides
    fun providesTopTenService(retrofit: Retrofit): TopTenService =
        retrofit.create(TopTenService::class.java)

    @Singleton
    @Provides
    fun providesTopTenDataSource(topTenDataSourceImpl: TopTenDataSourceImpl): TopTenDataSource = topTenDataSourceImpl

    @Singleton
    @Provides
    fun providesTopTenRepository(topTenRepositoryImpl: TopTenRepositoryImpl): TopTenRepository = topTenRepositoryImpl

}