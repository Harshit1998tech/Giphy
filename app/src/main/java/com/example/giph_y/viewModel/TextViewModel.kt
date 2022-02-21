package com.example.giph_y.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giph_y.Data

class TextViewModel : ViewModel(){
    private val mutableSelectedItem = MutableLiveData<Data>()
    val selectedItem: LiveData<Data> get() = mutableSelectedItem

    fun selectItem(data: Data) {
        mutableSelectedItem.value = data
    }
}