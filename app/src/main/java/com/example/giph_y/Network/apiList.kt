package com.example.giph_y.Network

import com.example.giph_y.GifInterface
import com.example.giph_y.ui.Gif
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://api.giphy.com/"
const val API_KEY = "ecvlMBqiiILhFt1EAmHAl28khmjt3U8P"

interface ApiList {
    @GET("v1/gifs/trending?api_key=${com.example.giph_y.API_KEY}")
    fun getAllgif(@Query("limit") limit: Int, @Query("offset") offset: Int): List<Gif>
}

    object apiService{
        val apiInstance : ApiList
        init{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiInstance = retrofit.create(ApiList::class.java)
        }
    }

