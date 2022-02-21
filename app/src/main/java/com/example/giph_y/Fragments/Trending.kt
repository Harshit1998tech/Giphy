package com.example.giph_y.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giph_y.Adapter.GifAdapter
import com.example.giph_y.Adapter.PagingAdapter
import com.example.giph_y.GifService
import com.example.giph_y.R
import com.example.giph_y.ui.Gif
import com.example.giph_y.viewModel.TrendingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_trending.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Trending : Fragment() {
    private lateinit var trendingViewModel: TrendingViewModel
    private lateinit var adapter: GifAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        trendingViewModel = ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_trending, container, false)
         val relistso = root.findViewById<RecyclerView>(R.id.relistso)
        getTrending()
        return root
    }
     fun getTrending() {
        val Gifo = GifService.GifInstance.getTrending(50, 50)
        Gifo.enqueue(object : Callback<Gif> {
            override fun onFailure(call: Call<Gif>, t: Throwable) {
                Log.d("done", "error in fetching gif", t)
            }
            override fun onResponse(call: Call<Gif>, response: Response<Gif>) {
                val Gifo = response.body()
                if (Gifo != null) {
                    Log.d("done", Gifo.toString())
                    adapter = GifAdapter(this@Trending, Gifo.data)
                    relistso.adapter = adapter
                    relistso.layoutManager = LinearLayoutManager(activity)
                }
            }
        })
    }
}




