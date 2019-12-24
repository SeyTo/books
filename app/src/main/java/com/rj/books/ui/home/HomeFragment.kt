package com.rj.books.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rj.books.R
import com.rj.books.ui.home.list.ProductListAdapter
import com.rj.books.ui.home.list.ProductListWrapper
import com.rj.books.ui.home.list.WrappedBookList
import com.rj.books.ui.home.list.WrappedQuestionsView
import com.rj.books.ui.util.recyclerview_decoration.BoundsSpacerDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var navController: NavController
    private lateinit var _ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this._ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        navController = findNavController()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        product_list.layoutManager = LinearLayoutManager(_ctx, LinearLayoutManager.VERTICAL, false)
        product_list.adapter =
            ProductListAdapter(
                _ctx,
                wrappersList(),
                bookListViewListener = {
                    // TODO insert book list
                    navController.navigate(HomeFragmentDirections.actionNavHomeToBookVerticalListFragment())
                },
                bookItemClickListener = {
                    navController.navigate(HomeFragmentDirections.actionNavHomeToNavBookDetails(it))
                }
            )
        product_list.addItemDecoration(BoundsSpacerDecoration(230))
    }

}

fun wrappersList(): ArrayList<ProductListWrapper> {
    return arrayListOf(
        WrappedBookList(title = "Horror"),
        WrappedBookList(title = "Medical"),
        WrappedBookList(title = "Commerce"),
        WrappedQuestionsView(),
//        WrappedBookList(title = "Self Help"),
//        WrappedBookList(title = "Language"),
        WrappedBookList(title = "DIY")
    )
}
