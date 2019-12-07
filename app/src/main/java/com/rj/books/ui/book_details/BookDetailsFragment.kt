package com.rj.books.ui.book_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rj.books.R
import com.rj.books.model.Seller
import com.rj.books.model.book.Book
import com.rj.books.ui.custom.sellerlist.SellerList

class BookDetailsFragment : Fragment() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_book_details, container, false)

//        val textView: TextView = root.findViewById(R.id.text_send)
//        bookViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

        val book: Book? = arguments?.getParcelable("book")
        Toast.makeText(activity, book.toString(), Toast.LENGTH_SHORT).show()

        val sellersList = root.findViewById<SellerList>(R.id.sellers)

        val sellers = arrayListOf(
            Seller("Mahatma Gandhi", ""),
            Seller("Putin", ""),
            Seller("Henry Djkovic", ""),
            Seller("Henry Cavill", ""),
            Seller("J. R. R. Tolkien", ""),
            Seller("Boy Wonder", ""),
            Seller("Hello Books", "")
        )
        sellersList.sellers = sellers
        sellersList.title = "Sellers"
//        sellersList.setOnSellerClickListener {
//            val direction = HomeFragmentDirections.actionNavHomeToNavBookDetails(it)
//            findNavController().navigate(direction)
//        }
        return root
    }
}