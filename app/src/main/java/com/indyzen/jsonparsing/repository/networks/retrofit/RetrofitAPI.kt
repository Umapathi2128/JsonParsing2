package com.indyzen.jsonparsing.repository.networks.retrofit

import com.indyzen.numberplatedetection.repository.networks.ServiceResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import rx.Observable


/**
 * Created by Umapathi on 31/12/18.
 * Copyright Indyzen Inc, @2018
 */
interface RetrofitAPI {


    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<ServiceResponse>

    @FormUrlEncoded
    @POST("api/login")
    fun login1(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ServiceResponse>

    // Synchronous declaration
    @GET("/my_api/shop_list")
    fun getMyThing1(@Query("mid") param1: String): ServiceResponse

//    // Asynchronous declaration
//    @GET("/my_api/shop_list")
//    fun getMyThing2(@Query("mid") param1: String, callback: Callback<ServiceResponse>)


    @Multipart
    @PUT("user/photo")
    fun updateUser(@Part("photo") photo: RequestBody, @Part("description") description: RequestBody): Call<ServiceResponse>
}