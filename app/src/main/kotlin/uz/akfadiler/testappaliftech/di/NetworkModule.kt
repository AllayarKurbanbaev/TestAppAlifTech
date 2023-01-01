package uz.akfadiler.testappaliftech.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.akfadiler.testappaliftech.BuildConfig.BASE_URL
import uz.akfadiler.testappaliftech.data.remote.ApiService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun getOkHTTPClient(): OkHttpClient = OkHttpClient.Builder().readTimeout(40, TimeUnit.SECONDS)
        .connectTimeout(40, TimeUnit.SECONDS).callTimeout(40, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @[Provides Singleton]
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()

    @[Provides Singleton]
    fun getApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}