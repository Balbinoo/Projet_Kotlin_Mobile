package com.ismin.android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface OeuvreService {

    @GET("oeuvres/all")
    fun getAllOeuvres(): Call<List<Oeuvre>>

    @POST("oeuvres")
    fun createOeuvre(@Body oeuvre: Oeuvre): Call <Oeuvre>

    @DELETE("oeuvres/delete/{id_oeuvre}")
    fun deleteOeuvre(@Path("id_oeuvre") id: String): Call<Void>

    @PUT("oeuvres/put/{id_oeuvre}")
    fun putFavorite(@Path("id_oeuvre") id: String, @Body updatedOeuvre: Oeuvre): Call<Void>

}