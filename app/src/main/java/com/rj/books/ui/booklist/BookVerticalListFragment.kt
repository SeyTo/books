package com.rj.books.ui.booklist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.rj.books.R
import com.rj.books.model.book.Book
import com.rj.books.ui.my_profile.booksList
import com.rj.books.ui.util.recyclerview_decoration.BoundsSpacerDecoration
import kotlinx.android.synthetic.main.fragment_book_details.*
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_book_list.toolbar

class BookVerticalListFragment : Fragment() {

    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_book_list, container, false)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toolbar.setupWithNavController(findNavController())
        book_list.adapter = BookVerticalListAdapter(ctx, booksList()) {
            findNavController().navigate(BookVerticalListFragmentDirections.actionNavBookVerticalListToNavBookDetails())
        }
        book_list.addItemDecoration(BoundsSpacerDecoration(200))
    }
}

class BookVerticalListAdapter(val _ctx: Context, var _books: ArrayList<Book>, private val _clickListener: ((book: Book) -> Unit)? = null): RecyclerView.Adapter<BookVerticalListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(_ctx).inflate(R.layout.book_vert_item, parent, false)
        return ViewHolder(root)
    }

    override fun getItemCount(): Int = _books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bookName.text = _books[position].name
        holder.bookAuthor.text = _books[position].author
//        holder.bookImage.text = _books[position].name
        holder.bookDescription.text = _books[position].description
        holder.itemView.setOnClickListener {
            _clickListener?.invoke(_books[position])
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val bookName: TextView = view.findViewById(R.id.book_name)
        val bookAuthor: TextView = view.findViewById(R.id.book_author)
        val bookImage: ImageView = view.findViewById(R.id.book_image)
        val bookDescription: TextView = view.findViewById(R.id.book_description)
    }
}