package com.rj.books.ui.sellers


import android.content.Context
import android.content.ReceiverCallNotAllowedException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rj.books.R
import com.rj.books.model.Seller
import com.rj.books.model.book.Book

/**
 * A simple [Fragment] subclass.
 */
class SellersFragment : Fragment() {

    private lateinit var sellerViewModel: SellerViewModel
    private lateinit var _context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this._context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sellerViewModel = ViewModelProviders.of(this).get(SellerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sellers, container, false)
        val sellers: RecyclerView = root.findViewById(R.id.sellers_list)

        sellers.layoutManager = LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false)
        sellers.adapter = SellerRecyclerAdapter(_context, sellers()) {
            Toast.makeText(_context, "CLicked", Toast.LENGTH_SHORT).show()
        }

        val toolbar: Toolbar = root.findViewById(R.id.toolbar)
        toolbar.setupWithNavController(findNavController())
        return root
    }
}

fun sellers(): ArrayList<Seller> {
    return arrayListOf(
        Seller("Babu Ram", ""),
        Seller("Jhil Maiyya", ""),
        Seller("Sano Babu", ""),
        Seller("Babu Sano", ""),
        Seller("Doggi", ""),
        Seller("Pret Aatma", "")
    )
}

class SellerRecyclerAdapter(
    private val _context: Context,
    private var _sellers: ArrayList<Seller> = ArrayList(),
    private var _clickListener: ((seller: Seller) -> Unit)? = null
): RecyclerView.Adapter<SellerRecyclerAdapter.SellerViewHolder>() {

    var sellers: ArrayList<Seller>
        get() = _sellers
        set(value) {
            _sellers = value
            notifyDataSetChanged()
        }

    var clickListener: ((seller: Seller) -> Unit)?
        get() = _clickListener
        set(value) {
            _clickListener = value
            // let onBindViewHolder refresh and add all the listeners
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder {
        val root: View = LayoutInflater.from(_context).inflate(R.layout.seller_vertical_item, parent, false)
        return SellerViewHolder(root)
    }

    override fun getItemCount(): Int = _sellers.size

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {
        holder.sellerName.text = sellers[position].name
        holder.itemView.setOnClickListener { clickListener?.invoke(sellers[position]) }
    }

    class SellerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val sellerName: TextView = itemView.findViewById(R.id.seller_name)
    }
}
