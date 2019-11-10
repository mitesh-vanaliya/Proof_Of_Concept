package com.arinspect.proof_of_concept.api

import com.arinspect.proof_of_concept.BuildConfig
import com.arinspect.proof_of_concept.application.ProofConceptApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * [RetrofitSingleton] : Retrofit singleton class provide singleton object for retrofit
 *
 * @author : Mitesh Vanaliya
 * @version 1.0.0
 * @since 11/08/2019
 */
class RetrofitSingleton private constructor() {
    object HOLDER {
        val instance = RetrofitSingleton()
    }

    companion object {
        @JvmStatic
        fun getInstance() = HOLDER.instance
    }

    private val httpCacheDirectory = File(ProofConceptApplication.context.cacheDir, "responses")
    private val cache = Cache(httpCacheDirectory, (10 * 1024 * 1024).toLong())

    /**
     * Create IAppWebApi object to get API
     */
    fun provideApiService(): IAppWebApi {
        return provideRetrofit().create(IAppWebApi::class.java)
    }

    /**
     *
     */
    private fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(provideGson())
    }

    /**
     * Create Gson class object
     */
    private fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    /**
     *
     */
    private fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    /**
     * Create Okhttp object using builder pattern
     * okhttp builder pattern add request time in second and Network intercepter
     */
    private fun getOkHttpClient(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.cache(cache)
        builder.hostnameVerifier { hostname, session ->
            hostname.equals(
                session.peerHost,
                ignoreCase = true
            )
        }
        builder.connectTimeout(80, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        builder.addNetworkInterceptor(networkCacheInterceptor())
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return builder
    }

    /**
     * Network interceptor class to add header in request and cache also
     */
    private fun networkCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(2, TimeUnit.MINUTES)
                .build()
            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
            response
        }
    }

    /**
     * Create retrofit class for baseUrl, adapterfactory and other point
     */
    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(provideGsonConverterFactory())
            .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
            .client(getOkHttpClient().build())
            .build()
    }
}