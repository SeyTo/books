package com.rj.books.ui.book_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Book Details Fragment"
    }
    val text: LiveData<String> = _text
}