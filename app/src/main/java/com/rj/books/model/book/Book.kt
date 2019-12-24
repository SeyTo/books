package com.rj.books.model.book

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Book(val name: String, val image: String = "", val author: String = "", val description: String = ""): Parcelable