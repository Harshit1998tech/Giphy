package com.example.giph_y.Network

import androidx.paging.PagingSource
import com.example.giph_y.ui.Gif
import retrofit2.HttpException
import java.io.IOException

class DataPagingSource constructor(private val apiList: ApiList) : PagingSource<Int, Gif>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val page = params.key?: 1
        return try {
            val response = apiList.getAllgif(page,params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page==1)null else page-1  ,
                nextKey = if (response.isEmpty()) null else page+1
            )
        }catch (e:IOException){
            LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }

    }
}