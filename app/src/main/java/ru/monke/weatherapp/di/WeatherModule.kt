package ru.monke.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.monke.weatherapp.BuildConfig
import ru.monke.weatherapp.data.weather.WeatherApi

@Module(includes = [WeatherBindings::class])
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    @WeatherClient
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .build()
    }

    @Provides
    fun provideWeatherApi(@WeatherClient retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

}