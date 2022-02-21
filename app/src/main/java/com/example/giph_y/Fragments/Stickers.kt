package com.example.giph_y.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giph_y.*
import com.example.giph_y.Adapter.StickerAdapter
import com.example.giph_y.ui.Gif
import com.example.giph_y.viewModel.StickersViewModel
import kotlinx.android.synthetic.main.fragment_stickers.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Stickers : Fragment() {
    private lateinit var stickersViewModel: StickersViewModel
    private lateinit var adapter: StickerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        stickersViewModel = ViewModelProviders.of(this).get(StickersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_stickers, container, false)
        val relists = root.findViewById<RecyclerView>(R.id.relists)
        getStickers()
        return root
    }
      fun getStickers() {
        val sticker = StickersService.StickersInstance.getStickers(100,50)
        sticker.enqueue(object: Callback<Gif> {
            override fun onFailure(call: Call<Gif>, t: Throwable) {
                Log.d("donee","error in fetching gif",t)
            }
            override fun onResponse(call: Call<Gif>, response: Response<Gif>) {
                val sticker = response.body()
                if(sticker!=null){
                    Log.d("donee",sticker.toString())
                    adapter = StickerAdapter(this@Stickers,sticker.data)
                    //val relists = views.findViewById<RecyclerView>(R.id.relists)
                    relists.adapter = adapter
                    relists.layoutManager = LinearLayoutManager(activity)
                }
            }
        })
    }
}

