package com.example.giph_y

import com.example.giph_y.ui.Gif
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URLs = "api.giphy.com/v1/gifs/search"
const val API_KEYs = "ecvlMBqiiILhFt1EAmHAl28khmjt3U8P"


interface SearchApi {
    @GET("v1/gifs/search?api_key=$API_KEYs")
    fun getSearch(@Query("limit")limit:Int,@Query("offset")offset:Int,@Query("q")q:String) : Call<Gif>
}

object SearchService{
     val SearchInstance : SearchApi
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        SearchInstance = retrofit.create(SearchApi::class.java)
    }
}