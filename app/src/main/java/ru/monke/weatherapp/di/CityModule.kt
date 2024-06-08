package ru.monke.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.monke.weatherapp.BuildConfig
import ru.monke.weatherapp.data.city.CityApi

@Module(includes = [CityBindings::class])
@InstallIn(SingletonComponent::class)
class CityModule {

    @Provides
    @CitiesClient
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.CITIES_BASE_URL)
            .build()
    }

    @Provides
    fun provideCityApi(@CitiesClient retrofit: Retrofit): CityApi {
        return retrofit.create(CityApi::class.java)
    }
}