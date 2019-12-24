package com.rj.books.ui.create_book_offer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateBookOfferViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Book Details Fragment"
    }
    val text: LiveData<String> = _text
}