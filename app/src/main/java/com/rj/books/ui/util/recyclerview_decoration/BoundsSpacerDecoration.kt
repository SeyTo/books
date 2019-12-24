package com.rj.books.ui.util.recyclerview_decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BoundsSpacerDecoration(val space: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when(parent.getChildAdapterPosition(view)) {
            0 -> outRect.top = space
            parent.adapter!!.itemCount - 1 -> outRect.bottom = space
        }
    }
}