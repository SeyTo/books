package com.rj.books.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.rj.books.R
import com.rj.books.model.book.Book
import com.rj.books.ui.custom.booklist.BookList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val bookList = root.findViewById<BookList>(R.id.book_list)

        val books = arrayListOf(Book("Dulcimer", ""), Book("1864", ""), Book("Utopia", ""))
        bookList.books = books
        bookList.title = "Hello Title"
        bookList.setOnBookClickListener {
            val direction = HomeFragmentDirections.actionNavHomeToNavBookDetails(it)
            findNavController().navigate(direction)
        }
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }
}