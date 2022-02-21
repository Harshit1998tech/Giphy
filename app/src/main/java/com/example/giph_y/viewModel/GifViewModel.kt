package com.example.giph_y.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.giph_y.Network.ApiList
import com.example.giph_y.Network.DataPagingSource
import com.example.giph_y.ui.Gif
import kotlinx.coroutines.flow.Flow

class GifViewModel constructor(private val apiList: ApiList) : ViewModel() {
     fun getAllgif(): Flow<PagingData<Gif>> = Pager(
         config = PagingConfig(20, enablePlaceholders = false),
    ){
         DataPagingSource(apiList)
     }.flow.cachedIn(viewModelScope)
}