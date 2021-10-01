package com.sst.testkumparan.di

import com.google.gson.GsonBuilder
import com.sst.testkumparan.common.Constant
import com.sst.testkumparan.data.remote.JsonPlaceholderAPI
import com.sst.testkumparan.data.repositories.JSONPlaceHolderImpl
import com.sst.testkumparan.domain.repositories.JsonPlaceholderRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideOkhttp(): OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()







    @Singleton
    @Provides
    fun provideRetrofit (okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()))
        .client(okHttpClient)
        .baseUrl(Constant.BASE_URL)
        .build()


    @Singleton
    @Provides
    fun providesServices(retrofit: Retrofit): JsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI::class.java)


    @Singleton
    @Provides
    fun providesJsonPlaceHolderRepository(api : JsonPlaceholderAPI) : JsonPlaceholderRepo {
        return JSONPlaceHolderImpl(api)
    }
}