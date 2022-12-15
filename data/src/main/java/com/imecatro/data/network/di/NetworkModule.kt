package com.imecatro.data.network.di

import com.google.gson.Gson
import com.imecatro.data.network.RepositoryImp
import com.imecatro.data.network.SpacexService
import com.imecatro.data.room.LaunchesDao
import com.imecatro.data.room.LocalDatabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {
    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesOkHttp(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()


    @Provides
    fun providesRetrofitObject(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(SpacexService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun providesSpacexService(retrofit: Retrofit): SpacexService =
        retrofit.create(SpacexService::class.java)

    @Provides
    fun provideNetworkRepositoryImplementation( spacexService: SpacexService) : RepositoryImp =
        RepositoryImp(spacexService)
}