package com.rj.books.ui.home.list

import com.rj.books.ui.home.list.ProductListWrapper

class WrappedBookList(val title: String):
    ProductListWrapper {
    override val type: Int
        get() = ProductListWrapper.WrapperType.BOOK_LIST.ordinal
}