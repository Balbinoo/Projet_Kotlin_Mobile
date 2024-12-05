package com.ismin.android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface OeuvreService {

    @GET("oeuvres/all")
    fun getAllOeuvres(): Call<List<Oeuvre>>

    @POST("oeuvres")
    fun createOeuvre(@Body oeuvre: Oeuvre): Call <Oeuvre>

}