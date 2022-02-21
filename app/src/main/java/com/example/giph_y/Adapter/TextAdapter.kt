package com.example.giph_y.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giph_y.Data
import com.example.giph_y.Fragments.Text
import com.example.giph_y.R

class TextAdapter(private val context: Text, private val data: List<Data>) : RecyclerView.Adapter<TextAdapter.TextViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return TextViewHolder(view)
    }
    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val gif = data[position]
        val url = gif.images.original.url
        holder.textView.text = gif.title
        Glide.with(context)
            .asGif()
            .load(url)
            .into(holder.imageview)

    }
    override fun getItemCount(): Int {
        return data.size
    }
    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageview = itemView.findViewById<ImageView>(R.id.imageview)
        var textView = itemView.findViewById<TextView>(R.id.textView)

    }

}