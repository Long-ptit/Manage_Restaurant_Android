package com.restaurant.exam.network

import com.restaurant.exam.data.model.Floor
import com.restaurant.exam.data.model.Food
import com.restaurant.exam.data.model.Restaurant
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface Api {

   @POST("api/v1/restaurant/loginRes")
   fun login(@Body request: Restaurant): Observable<Restaurant>

   @GET("api/v1/restaurant/getById/1")
   fun getId(): Observable<Restaurant>


   @GET("api/v1/floor/getByIdRes/{id}")
   fun getFloor(@Path("id") id: Int): Observable<List<Floor>>

   @GET("api/v1/food/getFoodByIdRes/{id}")
   fun getFood(@Path("id") id: Int): Observable<List<Food>>

}