package com.restaurant.exam.network

import com.restaurant.exam.data.model.Restaurant
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {

   @POST("api/v1/restaurant/loginRes")
   fun login(@Body request: Restaurant): Observable<Restaurant>

   @GET("api/v1/restaurant/getById/1")
   fun getId(): Observable<Restaurant>

   }