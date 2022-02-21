package com.example.giph_y.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.giph_y.databinding.ErrorStateBinding

class LoadingStateAdapter constructor(private val retry : ()->Unit)
    : LoadStateAdapter<LoadingStateAdapter.LoadingViewHolder>() {

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingViewHolder {
        return LoadingViewHolder(ErrorStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),retry)
    }


    class LoadingViewHolder(private val binding: ErrorStateBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.errorbutton.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                errorbutton.isVisible = loadState !is LoadState.Loading
                errortext.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}