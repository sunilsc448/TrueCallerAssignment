package com.example.truecallerassignment.modules

import com.example.truecallerassignment.api.ProjectApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://blog.truecaller.com"

//in case a api key need to be added as query parameter
private const val API_KEY_KEY = ""
private const val API_KEY = ""

@Module
class ApiModule {

    @Provides
    @Singleton
    fun getInterceptor():Interceptor{
        return Interceptor { chain ->  
            val newUrl = chain.request().url().newBuilder().addQueryParameter(API_KEY_KEY, API_KEY).build()
            val newRequest = chain.request().newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun getHttpClient(interceptor: Interceptor):OkHttpClient{
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Provides
    fun getService(retrofit: Retrofit): ProjectApiService {
        return retrofit.create(ProjectApiService::class.java)
    }
}