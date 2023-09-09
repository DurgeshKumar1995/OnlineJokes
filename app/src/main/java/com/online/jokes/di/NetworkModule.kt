package com.online.jokes.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.online.jokes.data.network.RestApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
* Rest Api request methods and set in KOIN dependency
* */
object NetworkModule {

    // wait and time out times
    private const val waitTimeOut: Long = 30
    private const val connTimeOut: Long = 10
    private const val baseUrl = "https://geek-jokes.sameerkumar.website/"

    val module = module {
        factory { gson() }
        factory { gsonConverter(get()) }
        factory { adapterFactory() }
        factory { provideLoggingInterceptor() }
        factory { provideOkHttpClient(get()) }
        single { provideRetrofit(get(), get(), get()) }
        factory { service(get()) }
    }

    /*
    * create OkHttpClient
    * @param app , Application object for cache directory path and
    * CacheInterceptor for network status check
    *
    * @return OkHttpClient object
    * */
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(logging)
        }
        return builder
            .callTimeout(waitTimeOut, TimeUnit.SECONDS)
            .connectTimeout(connTimeOut, TimeUnit.SECONDS)
            .readTimeout(waitTimeOut, TimeUnit.SECONDS)
            .build()
    }

    /*
    * return logging
    * */
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    /*
    * create and return GsonBuilder object
    * */
    fun gson() = GsonBuilder()
        .create()

    /*
   * create and return GsonConverterFactory object
   * */
    fun gsonConverter(gson: Gson) = GsonConverterFactory.create(gson)

    /*
   * create and return GsonConverterFactory object
   * */
    fun adapterFactory() = CoroutineCallAdapterFactory()

    /*
    * create retrofit object
    * @param client, OkHttpClient add in retrofit object
    * Add GsonConverterFactory as converter factory
    * Add CoroutineCallAdapterFactory rest api handle data
    * @return retrofit object
    * */
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        adapterFactory: CoroutineCallAdapterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(adapterFactory)
            .build()
    }

    /*
    * @param retrofit, retrofit object access all method of interface
    * @return RESTApi instance
    * */
    fun service(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)
}
