package com.example.giph_y.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.giph_y.Fragments.*

internal class MyAdapter(var context: Context, fm : FragmentManager,  var totaltabs : Int) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                Trending()
            }
            1-> {
                Stickers()
            }
            2-> {
                Text()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totaltabs
    }
}
