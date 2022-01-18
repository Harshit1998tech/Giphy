package com.example.giph_y

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GifAdapter( val context: Context,  val data: List<Data>) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gif = data[position]
        holder.textView.text = gif.title
        Glide.with(context).load(gif.embed_url).into(holder.imageview)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageview = itemView.findViewById<ImageView>(R.id.imageview)
        var textView = itemView.findViewById<TextView>(R.id.textView)
    }
}