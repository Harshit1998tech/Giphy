package com.example.giph_y

import com.example.giph_y.ui.Gif
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.giphy.com/"
const val API_KEY = "ecvlMBqiiILhFt1EAmHAl28khmjt3U8P"

 interface GifInterface{
    @GET("v1/gifs/trending?api_key=$API_KEY")
      fun getTrending(@Query("limit")limit:Int,@Query("offset")offset:Int): Call<Gif>
}

object GifService{
    val GifInstance : GifInterface
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        GifInstance = retrofit.create(GifInterface::class.java)
    }
}


interface StickInterface{
    @GET("v1/stickers/trending?api_key=$API_KEY")
     fun getStickers(@Query("limit")limit:Int,@Query("offset")offset:Int) : Call<Gif>
}
object StickersService{
    val StickersInstance : StickInterface
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        StickersInstance = retrofit.create(StickInterface::class.java)
    }
}

interface TextInterface{
    @GET("v1/text/trending?api_key=$API_KEY")
    fun getText(@Query("limit")limit:Int,@Query("offset")offset:Int) : Call<Gif>
}
object TextService{
    val TextInstance : TextInterface
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        TextInstance = retrofit.create(TextInterface::class.java)
    }
}