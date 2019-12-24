package com.rj.books.ui.custom.sellerlist

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rj.books.R
import com.rj.books.model.Seller

class SellerList: LinearLayout {

    private var _title: String? = null
    private var _sellers: ArrayList<Seller>? = null

    private var sellersList: RecyclerView? = null
    private var header: TextView? = null

    /**
     * Title.
     */
    var title: String?
        get() = _title
        set(value) {
            _title = value
            header!!.text = value
        }

    var sellers: ArrayList<Seller>
        get() = _sellers!!
        set(value) {
            _sellers = value
            (sellersList!!.adapter as SellerListAdapter).sellers = _sellers!!
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.SellerList, defStyle, 0
        )

        _title = a.getString(R.styleable.SellerList_title) ?: ""
//        _titleStyle = if (a.hasValue(R.styleable.SellerList_title_style)) a.getInt(R.styleable.SellerList_title_style) : R.
//        if (a.hasValue(R.styleable.BookShortList_exampleDrawable)) {
//            exampleDrawable = a.getDrawable(
//                R.styleable.BookShortList_exampleDrawable
//            )
//            exampleDrawable?.callback = this
//        }

        a.recycle()

        // Set up a default TextPaint object
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.sellers_list, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        header = findViewById(R.id.header)
        sellersList = findViewById(R.id.recycler_view)
        sellersList?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = SellerListAdapter(context)
            addItemDecoration(SpacerDecoration(70))
        }
    }

    public fun setOnSellerClickListener(sellerClickListener: (seller: Seller) -> Unit) {
        (sellersList!!.adapter as SellerListAdapter).clickListener = sellerClickListener
    }

    private class SpacerDecoration(val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.right = space
            outRect.left = if (parent.getChildAdapterPosition(view) != 0) 0 else space
        }
    }
}