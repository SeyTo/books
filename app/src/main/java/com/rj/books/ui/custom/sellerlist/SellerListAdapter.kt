package com.rj.books.ui.custom.sellerlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rj.books.R
import com.rj.books.model.Seller

class SellerListAdapter(
    private val _context: Context,
    private var _sellers: ArrayList<Seller> = ArrayList(),
    private var _clickListener: ((seller: Seller) -> Unit)? = null
): RecyclerView.Adapter<SellerListAdapter.SellersListAdapterViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellersListAdapterViewHolder {
        val root = LayoutInflater.from(_context)
            .inflate(R.layout.seller_item, parent, false)
        return SellersListAdapterViewHolder(root)
    }

    override fun getItemCount(): Int = sellers.size

    override fun onBindViewHolder(holder: SellersListAdapterViewHolder, position: Int) {
        holder.sellerName.text = sellers[position].name
        holder.itemView.setOnClickListener { clickListener?.let { listener -> listener(sellers[position]) } }
    }

    class SellersListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sellerImage: ImageView = itemView.findViewById(R.id.seller_image)
        val sellerName: TextView = itemView.findViewById(R.id.seller_name)
    }

    operator fun plus(seller: Seller) {
        sellers.add(seller)
        notifyItemInserted(sellers.size)
    }

}
