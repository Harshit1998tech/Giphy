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
import com.example.giph_y.Adapter.TextAdapter
import com.example.giph_y.R
import com.example.giph_y.StickersService
import com.example.giph_y.TextService
import com.example.giph_y.ui.Gif
import com.example.giph_y.viewModel.TextViewModel
import kotlinx.android.synthetic.main.fragment_stickers.*
import kotlinx.android.synthetic.main.fragment_stickers.relists
import kotlinx.android.synthetic.main.fragment_text.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Text : Fragment() {
    private lateinit var textViewModel: TextViewModel
    private lateinit var adapter: TextAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        textViewModel = ViewModelProviders.of(this).get(TextViewModel::class.java)
        val root= inflater.inflate(R.layout.fragment_text, container, false)
        val textlist = root.findViewById<RecyclerView>(R.id.textlist)
        getText()
        return root
    }
      fun getText() {
          val text = TextService.TextInstance.getText(100,50)
          text.enqueue(object: Callback<Gif> {
              override fun onFailure(call: Call<Gif>, t: Throwable) {
                  Log.d("donee","error in fetching gif",t)
              }
              override fun onResponse(call: Call<Gif>, response: Response<Gif>) {
                  val text = response.body()
                  if(text!=null){
                      Log.d("donee",text.toString())
                      adapter = TextAdapter(this@Text,text.data)
                      //val relists = views.findViewById<RecyclerView>(R.id.relists)
                      textlist.adapter = adapter
                      textlist.layoutManager = LinearLayoutManager(activity)
                  }
              }
          })
      }
      }


