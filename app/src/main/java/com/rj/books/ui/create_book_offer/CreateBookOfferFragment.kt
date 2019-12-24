package com.rj.books.ui.create_book_offer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rj.books.R
import com.rj.books.ui.sell_book.SellABookViewModel

/**
 * A simple [Fragment] subclass.
 */
class CreateBookOfferFragment : Fragment() {

    private lateinit var sellABookViewModel: CreateBookOfferViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sellABookViewModel = ViewModelProviders.of(this).get(CreateBookOfferViewModel::class.java)
        val root: View = inflater.inflate(R.layout.fragment_create_book_offer, container, false)

        val toolbar: Toolbar = root.findViewById(R.id.toolbar)
        toolbar.setupWithNavController(findNavController())

        return root
    }


}
