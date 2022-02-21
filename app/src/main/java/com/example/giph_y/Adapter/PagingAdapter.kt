package com.example.giph_y.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giph_y.Data
import com.example.giph_y.Fragments.Text
import com.example.giph_y.R
import com.example.giph_y.ui.Gif


class PagingAdapter(private val context: Context, private val data: List<Data>) :
    PagingDataAdapter<Gif, PagingAdapter.PagingViewHolder>(GifComparator){

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val gif = data[position]
        val url = gif.images.original.url
        holder.textView.text = gif.title
        Glide.with(context)
            .asGif()
            .load(url)
            .into(holder.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )
    }

    class PagingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageview = itemView.findViewById<ImageView>(R.id.imageview)
        var textView = itemView.findViewById<TextView>(R.id.textView)
    }

    object GifComparator : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem.data == newItem.data
        }

        override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem == newItem
        }
    }

}
