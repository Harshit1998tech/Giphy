package com.example.giph_y.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giph_y.Data
import com.example.giph_y.Fragments.Stickers
import com.example.giph_y.R


class StickerAdapter(val context: Stickers, private val data: List<Data>) : RecyclerView.Adapter<StickerAdapter.StickersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickersViewHolder {
        val views = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return StickersViewHolder(views)
    }
    override fun onBindViewHolder(holder: StickersViewHolder, position: Int) {
        val gif = data[position]
        val url = gif.images.original.url
        holder.textView.text = gif.title
        Glide.with(context)
          //  .asGif()
            .load(url)
            .into(holder.imageview)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class StickersViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var imageview = itemView.findViewById<ImageView>(R.id.imageview)
        var textView = itemView.findViewById<TextView>(R.id.textView)
    }
}