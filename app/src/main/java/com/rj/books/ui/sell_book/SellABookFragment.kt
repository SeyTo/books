package com.rj.books.ui.sell_book

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rj.books.R
import com.rj.books.model.book.Book
import com.rj.books.ui.custom.booklist.BooksListAdapter
import com.rj.books.ui.my_profile.booksList
import kotlinx.android.synthetic.main.fragment_sell_a_book.*

class SellABookFragment : Fragment() {

    enum class Mode {
        SELL, REQUEST
    }

    companion object {
        const val TAG = "SellABookFragment"
    }

    private lateinit var sellABookViewModel: SellABookViewModel
    private lateinit var _ctx: Context
    private var currentMode: Mode = Mode.SELL

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sellABookViewModel =
                ViewModelProviders.of(this).get(SellABookViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sell_a_book, container, false)

        val confirm: FloatingActionButton = root.findViewById(R.id.confirm)
        confirm.setOnClickListener {
            val direction = SellABookFragmentDirections.actionNavSellABookToCreateBookOfferFragment()
            findNavController().navigate(direction)
        }

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _ctx = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switch_mode.setOnClickListener {
            val newMode = if (currentMode == Mode.REQUEST) Mode.SELL else Mode.REQUEST
            switchMode(newMode)
            currentMode = newMode
        }
        book_cover.adapter = BookCoverPagerAdapter(_ctx, arrayListOf(Book("test name")))
        book_cover.maxPageScale = 1F
    }

    private fun switchMode(mode: Mode) {
        when (mode) {
            Mode.SELL -> {
                switch_mode.text = "Request A Book"
                form_title_name.text = "ISBN Number"
                title.text = "Sell A Book"
                // TODO apply validation
            }
            Mode.REQUEST -> {
                switch_mode.text = "Sell A Book"
                form_title_name.text = "ISBN Number or Book Name"
                title.text = "Request A Book"
                // TODO apply validation
            }
        }
    }

    /**
     * @param value either an ISBN number or a name
     */
    private fun findBook(value: String) {

    }

}

class BookCoverPagerAdapter(val _ctx: Context, val _books: ArrayList<Book>): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = _books.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val root = LayoutInflater.from(_ctx).inflate(R.layout.book_item, container, false)
        container.addView(root)
        return root
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}