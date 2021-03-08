package com.products.data

import com.products.data.datasource.CatalogRemoteDataSource
import com.products.data.repository.CatalogRepository
import com.products.data.usecase.FetchCatalogUseCase
import com.products.network.service.CatalogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    // / Provide Remote Data Sources ///

    @Provides
    fun provideRemoteDataSource(catalogService: CatalogService): CatalogRemoteDataSource {
        return CatalogRemoteDataSource(catalogService)
    }

    // / Provide repositories ///

    @Singleton
    @Provides
    fun provideCompetitionsRepository(
        catalogRemoteDataSource: CatalogRemoteDataSource
    ): CatalogRepository {
        return CatalogRepository(catalogRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideMovieUseCase(
        catalogRepository: CatalogRepository
    ): FetchCatalogUseCase {
        return FetchCatalogUseCase(catalogRepository)
    }
}
