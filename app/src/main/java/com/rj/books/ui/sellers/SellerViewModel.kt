package com.rj.books.ui.sellers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SellerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Book Details Fragment"
    }
    val text: LiveData<String> = _text
}