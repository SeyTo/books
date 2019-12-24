package com.rj.books.ui.home.list

interface ProductListWrapper {
    enum class WrapperType {
        BOOK_LIST, QUERY, PROGRESS, NOTHING
    }
    val type: Int
}