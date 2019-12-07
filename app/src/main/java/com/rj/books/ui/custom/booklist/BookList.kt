package com.rj.books.ui.custom.booklist

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
import com.rj.books.model.book.Book

class BookList: LinearLayout {

    private var _title: String? = null
    private var _books: ArrayList<Book>? = null

    private var booksList: RecyclerView? = null
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

    var books: ArrayList<Book>
        get() = _books!!
        set(value) {
            _books = value
            (booksList!!.adapter as BooksListAdapter).books = _books!!
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
            attrs, R.styleable.BookList, defStyle, 0
        )

        _title = a.getString(
            R.styleable.BookList_title
        )
//        if (a.hasValue(R.styleable.BookShortList_exampleDrawable)) {
//            exampleDrawable = a.getDrawable(
//                R.styleable.BookShortList_exampleDrawable
//            )
//            exampleDrawable?.callback = this
//        }

        a.recycle()

        // Set up a default TextPaint object
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.books_list, this)


        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        header = findViewById(R.id.header)
        booksList = findViewById(R.id.recycler_view)
        booksList?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = BooksListAdapter(context)
            addItemDecoration(SpacerDecoration(30))
        }
    }

    public fun setOnBookClickListener(booksClickListener: (book: Book) -> Unit) {
        (booksList!!.adapter as BooksListAdapter).clickListener = booksClickListener
    }

    private fun invalidateTextPaintAndMeasurements() {
//        textPaint?.let {
//            it.textSize = exampleDimension
//            it.color = exampleColor
//            textWidth = it.measureText(exampleString)
//            textHeight = it.fontMetrics.bottom
//        }
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


