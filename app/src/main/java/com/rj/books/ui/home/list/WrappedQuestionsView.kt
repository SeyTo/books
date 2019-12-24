package com.rj.books.ui.home.list

import com.rj.books.ui.home.list.ProductListWrapper

class WrappedQuestionsView: ProductListWrapper {
    override val type: Int
        get() = ProductListWrapper.WrapperType.QUERY.ordinal
}