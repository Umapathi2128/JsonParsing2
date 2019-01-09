package com.indyzen.jsonparsing.repository.networks.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Umapathi on 31/12/18.
 * Copyright Indyzen Inc, @2018
 */
class RetrofitConfig {

    val baseUrl = "http://healingg.innovination.com/"

    private var buildRetrofitData: Retrofit? = null


    fun getRetrofit(): Retrofit? {

/*
val httpClient = OkHttpClient.Builder()
val interceptor = HttpLoggingInterceptor()
interceptor.level = HttpLoggingInterceptor.Level.BODY
httpClient.interceptors().add(interceptor)
val retrofit:Retrofit = createAdapter().build()
val service = retrofit.create(ServiceResponse::class.java!!)
*/

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
//            .interceptors().add(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()

//        val okBuilder = OkHttpClient.Builder()
//        okBuilder.addInterceptor(
//            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC).setLevel
//                (HttpLoggingInterceptor.Level.BODY).setLevel(HttpLoggingInterceptor.Level.HEADERS)
//        )

        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        val gson = gsonBuilder.create()

        return if (buildRetrofitData == null) {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        } else buildRetrofitData
    }

}