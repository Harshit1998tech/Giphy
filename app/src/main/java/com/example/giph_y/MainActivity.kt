package com.example.giph_y

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: GifAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getGif()

    }

    private fun getGif() {
       val Gif = GifService.GifInstance.getTrending(10,5)
        Gif.enqueue(object: Callback<Gif>{
            override fun onFailure(call: Call<Gif>, t: Throwable) {
            Log.d("done","error in fetching news",t)
        }
            override fun onResponse(call: Call<Gif>, response: Response<Gif>) {
              val Gif = response.body()
                if(Gif!=null){
                    Log.d("done",Gif.toString())
                   adapter = GifAdapter(this@MainActivity,Gif.data)
                    val relist = findViewById<RecyclerView>(R.id.relist)
                    relist.adapter = adapter
                    relist.layoutManager = LinearLayoutManager(this@MainActivity)

                }
            }


        })
    }
}




