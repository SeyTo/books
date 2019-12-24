package com.rj.books.ui.book_details

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.rj.books.R
import com.rj.books.model.Seller
import com.rj.books.model.book.Book
import com.rj.books.ui.login.LoginActivity
import com.rj.books.ui.login.LoginViewModel
import com.rj.books.ui.login.LoginViewModelFactory
import com.rj.books.utils.colorizeStatusBar
import kotlinx.android.synthetic.main.fragment_book_details.*

class BookDetailsFragment : Fragment() {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var _context: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_book_details, container, false)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setupWithNavController(findNavController())

        val book: Book? = arguments?.getParcelable("book")

        book_buy.setOnClickListener {
            var direction = BookDetailsFragmentDirections.actionNavBookDetailsToSellersFragment()
            findNavController().navigate(direction)
        }

        Glide.with(_context).load(R.drawable.book).into(book_image).waitForLayout()
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.book)
        createPaletteAsync(bitmap) {
            book_color.setColorFilter(it)
            colorizeStatusBar(activity!!, it)
            Toast.makeText(_context, "$it", Toast.LENGTH_SHORT).show()
        }

        sellers_short_list.sellers = getSellers()

        bookmark.setOnClickListener {
            val loginViewModel = ViewModelProviders.of(this,
                LoginViewModelFactory()
            ).get(LoginViewModel::class.java)
            if (!loginViewModel.isLoggedIn()) {
                findNavController().navigate(R.id.nav_login_activity)
            } else {
                Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inline fun createPaletteAsync(bitmap: Bitmap, crossinline callback: (color: Int) -> Unit) {
        Palette.from(bitmap).generate { palette ->
            palette?.lightVibrantSwatch?.bodyTextColor?.apply { callback(this) }
        }
    }

}

fun getSellers(): ArrayList<Seller> {
    return arrayListOf(
        Seller("Test Seller", ""),
        Seller("Test Seller", ""),
        Seller("Test Seller", ""),
        Seller("Test Seller", ""),
        Seller("Test Seller", ""),
        Seller("Test Seller", ""),
        Seller("Test Seller", "")
    )
}