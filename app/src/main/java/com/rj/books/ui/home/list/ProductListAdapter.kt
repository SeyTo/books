package com.rj.books.ui.home.list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rj.books.model.book.Book
import com.rj.books.ui.custom.QuestionsView
import com.rj.books.ui.custom.booklist.BookList
import com.rj.books.ui.my_profile.booksList

class ProductListAdapter(
    val _ctx: Context,
    val wrappers: ArrayList<ProductListWrapper>,
    val bookListViewListener: ((books: ArrayList<Book>) -> Unit)? = null,
    val bookItemClickListener: ((book: Book) -> Unit)? = null
): RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return when (viewType) {
            ProductListWrapper.WrapperType.BOOK_LIST.ordinal -> BookListViewHolder(
                BookList(_ctx)
            )
            ProductListWrapper.WrapperType.QUERY.ordinal -> QueryViewHolder(
                QuestionsView(_ctx)
            )
            ProductListWrapper.WrapperType.PROGRESS.ordinal -> TODO("Need to work on progress viewholder")
            else -> TODO("Need to work on nothing viewholder")
        }
    }

    override fun getItemCount(): Int = wrappers.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        if (getItemViewType(position) == ProductListWrapper.WrapperType.BOOK_LIST.ordinal) {
            (holder.itemView as BookList).also { bookList ->
                val wrappedBookList: WrappedBookList = (wrappers[position] as WrappedBookList)
                bookList.books = booksList()
                bookList.title = wrappedBookList.title
                bookList.header?.setOnClickListener {
                    bookListViewListener?.invoke(bookList.books)
                }
                bookList.setOnBookClickListener { book ->
                    bookItemClickListener?.invoke(book)
                }
            }
        } else if (getItemViewType(position) == ProductListWrapper.WrapperType.QUERY.ordinal) {
            (holder.itemView as QuestionsView).also {
            }
        } else {
            TODO("Need to work on more types of holders first")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return wrappers[position].type
    }

    abstract class ProductListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    class BookListViewHolder(booklist: BookList): ProductListViewHolder(booklist)

    class QueryViewHolder(questionsView: QuestionsView): ProductListViewHolder(questionsView)
}