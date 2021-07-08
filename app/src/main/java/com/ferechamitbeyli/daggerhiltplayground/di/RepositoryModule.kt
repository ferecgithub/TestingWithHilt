package com.ferechamitbeyli.daggerhiltplayground.di

import com.ferechamitbeyli.daggerhiltplayground.repository.MainRepository
import com.ferechamitbeyli.daggerhiltplayground.retrofit.BlogRetrofit
import com.ferechamitbeyli.daggerhiltplayground.retrofit.NetworkMapper
import com.ferechamitbeyli.daggerhiltplayground.room.BlogDao
import com.ferechamitbeyli.daggerhiltplayground.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}














