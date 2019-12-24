package com.rj.books.ui.my_profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rj.books.R
import com.rj.books.model.book.Book
import kotlinx.android.synthetic.main.fragment_my_profile.*


class MyProfileFragment : Fragment(), ViewTreeObserver.OnGlobalLayoutListener {

    companion object {
        private val TAG = MyProfileFragment::class.qualifiedName
        private const val COLLAPSING_TOOLBAR_MIN_HEIGHT = 126
    }

    private lateinit var myProfileViewModel: MyProfileViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<NestedScrollView>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myProfileViewModel =
                ViewModelProviders.of(this).get(MyProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_profile, container, false)

        appBarAnimationInit()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        my_books_for_sale.books = booksList()
        my_bookmarked_books.books = booksList()
        my_requested_books.books = booksList()
        my_read_books.books = booksList()


        settings.setOnClickListener {
            val direction = MyProfileFragmentDirections.actionNavMyProfileToNavMyBookmarks()
            findNavController().navigate(direction)
        }

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_scroll)
        container.viewTreeObserver.addOnGlobalLayoutListener(this)
//        bottomSheetBehavior.peekHeight = bottomSheetHeight
    }

    override fun onGlobalLayout() {
        container.viewTreeObserver.removeOnGlobalLayoutListener(this)
        // trying to fix the peek height of bottom sheet layout by looking at the view above
        val bottomSheetHeight = container.height - profile_container.height
        val ph: Int = profile_container.height
        val ah: Int = container.height
        bottomSheetBehavior.peekHeight = bottomSheetHeight
        Log.d(TAG, "$bottomSheetHeight $ph $ah")
    }

    private fun appBarAnimationInit() {
//        val appbar: AppBarLayout = root.findViewById(R.id.appbar)
//        val appbarCollapsingToolbar: CollapsingToolbarLayout = root.findViewById(R.id.appbar_collapsing_toolbar)
//        appbarCollapsingToolbar.minimumHeight = COLLAPSING_TOOLBAR_MIN_HEIGHT

//        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, p ->
//            Log.d(TAG, p.toString())
//        })
    }
}

fun booksList(): ArrayList<Book> {
    return arrayListOf(
        Book("Test Book", ""),
        Book("Test Book", ""),
        Book("Test Book", ""),
        Book("Test Book", ""),
        Book("Test Book", ""),
        Book("Test Book", ""),
        Book("Test Book", "")
    )
}
