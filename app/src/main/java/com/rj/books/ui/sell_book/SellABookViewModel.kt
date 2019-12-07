package com.rj.books.ui.sell_book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SellABookViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sell a book Fragment"
    }
    val text: LiveData<String> = _text
}