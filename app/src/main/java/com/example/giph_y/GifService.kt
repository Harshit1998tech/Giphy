package com.example.giph_y

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://api.giphy.com/"
const val API_KEY = "ecvlMBqiiILhFt1EAmHAl28khmjt3U8P"

interface GifInterface {
    @GET("v1/gifs/trending?api_key=$API_KEY")
    fun getTrending(@Query("limit")limit:Int,@Query("offset")offset:Int) : Call<Gif>
}

object GifService{
    val GifInstance : GifInterface
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl( BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        GifInstance = retrofit.create(GifInterface::class.java)
    }
}