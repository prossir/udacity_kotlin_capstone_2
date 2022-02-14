package com.udacity.asteroidradar.utils.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.BuildConfig.NASA_API_KEY
import com.udacity.asteroidradar.BuildConfig.DEBUG_MODE
import okhttp3.Cache
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


private const val CONNECTION_TIMEOUT_DEFAULT: Long = 5
private const val READ_TIMEOUT_DEFAULT: Long = 3
private const val WRITE_TIMEOUT_DEFAULT: Long = 5

private const val HEADER_NAME_AUTHORIZATION = "api_key"
private const val HEADER_NAME_ACCESS_TOKEN = NASA_API_KEY

fun createOkHttpClient(
    cache: Cache? = null,
    f: (Request.Builder.() -> Request.Builder)? = null
): OkHttpClient {
    val dispatcher = Dispatcher()
    dispatcher.maxRequests = 20
    dispatcher.maxRequestsPerHost = 5

    return OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = (f?.invoke(requestBuilder) ?: requestBuilder)
            val originalHttpUrl = chain.request().url
            val newUrl = originalHttpUrl.newBuilder()
                .addQueryParameter(HEADER_NAME_AUTHORIZATION, HEADER_NAME_ACCESS_TOKEN).build()
            request.url(newUrl)
            chain.proceed(request.build())
        }
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = if (DEBUG_MODE) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .writeTimeout(WRITE_TIMEOUT_DEFAULT, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT_DEFAULT, TimeUnit.MINUTES)
        .connectTimeout(CONNECTION_TIMEOUT_DEFAULT, TimeUnit.MINUTES)
        .dispatcher(dispatcher)
        .build()
}

fun createRetrofit(httpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            MoshiConverterFactory.create(Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory()).build()).asLenient()
        )
        .client(httpClient)
        .build()
}