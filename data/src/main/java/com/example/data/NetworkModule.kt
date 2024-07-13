package com.example.data

import android.util.Log
import com.example.data.utils.ApiConstants
import com.example.data.webservice.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { response ->
            Log.e("api", response)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofitInstance(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideWebServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }

}