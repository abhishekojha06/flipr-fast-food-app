package com.example.fliprfastfood.api

import android.content.ClipData
import android.media.RouteListingPreference
import retrofit2.http.*

interface ApiService {
    @GET("item")
    suspend fun getItems(): List<RouteListingPreference.Item>

    @POST("item")
    suspend fun addItem(@Body item: ClipData.Item): RouteListingPreference.Item

    @PUT("item/{id}")
    suspend fun updateItem(@Path("id") id: Int, @Body item: ClipData.Item): RouteListingPreference.Item

    @DELETE("item/{id}")
    suspend fun deleteItem(@Path("id") id: Int)
}
