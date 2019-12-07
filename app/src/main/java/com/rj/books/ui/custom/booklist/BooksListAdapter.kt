package com.rj.books.ui.custom.booklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rj.books.R
import com.rj.books.model.book.Book

class BooksListAdapter(
    private val _context: Context,
    private var _books: ArrayList<Book> = ArrayList(),
    private var _clickListener: ((book: Book) -> Unit)? = null
): RecyclerView.Adapter<BooksListAdapter.BooksListAdapterViewHolder>() {

    var books: ArrayList<Book>
        get() = _books
        set(value) {
            _books = value
            notifyDataSetChanged()
        }

    var clickListener: ((book: Book) -> Unit)?
        get() = _clickListener
        set(value) {
            _clickListener = value
            // let onBindViewHolder refresh and add all the listeners
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListAdapterViewHolder {
        val root = LayoutInflater.from(_context)
            .inflate(R.layout.book_item, parent, false)
        return BooksListAdapterViewHolder(root)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BooksListAdapterViewHolder, position: Int) {
        holder.bookName.text = books[position].name
        holder.itemView.setOnClickListener { clickListener?.let { listener -> listener(books[position]) } }
    }

    class BooksListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImage: ImageView = itemView.findViewById(R.id.book_image)
        val bookName: TextView = itemView.findViewById(R.id.book_name)
    }

    operator fun plus(book: Book) {
        books.add(book)
        notifyItemInserted(books.size)
    }

}
