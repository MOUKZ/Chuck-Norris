package com.omarmouki.chucknorris.feature_joke.di

import com.omarmouki.chucknorris.feature_joke.data.remote.ChuckNorrisApi
import com.omarmouki.chucknorris.feature_joke.data.repositoy.JokesRepositoryImpl
import com.omarmouki.chucknorris.feature_joke.domin.repository.JokesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JokeModule {
    @Provides
    @Singleton
    fun providesChuckNorrisApi(): ChuckNorrisApi {
        return Retrofit.Builder().baseUrl(ChuckNorrisApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ChuckNorrisApi::class.java)
    }

    @Provides
    @Singleton
    fun providesJokesRepository(api: ChuckNorrisApi): JokesRepository {
        return JokesRepositoryImpl(
            api
        )
    }
}