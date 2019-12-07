package com.rj.books.ui.my_books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rj.books.R

class MyBooksFragment : Fragment() {

    private lateinit var myBooksViewModel: MyBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBooksViewModel =
                ViewModelProviders.of(this).get(MyBooksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_books, container, false)
        val textView: TextView = root.findViewById(R.id.text_share)
        myBooksViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}