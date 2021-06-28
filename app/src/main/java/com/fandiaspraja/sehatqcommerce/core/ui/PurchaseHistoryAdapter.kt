package com.fandiaspraja.sehatqcommerce.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fandiaspraja.sehatqcommerce.R
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product
import com.fandiaspraja.sehatqcommerce.databinding.ItemProductBinding
import com.fandiaspraja.sehatqcommerce.databinding.ItemProductHistoryBinding
import java.util.ArrayList

class PurchaseHistoryAdapter : RecyclerView.Adapter<PurchaseHistoryAdapter.ListViewHolder>() {

    private var listData = ArrayList<Product>()
    var onItemClick: ((Product) -> Unit)? = null

    fun setData(newListData: List<Product>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_history, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemProductHistoryBinding.bind(itemView)
        fun bind(data: Product) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemPrice.text = data.price
            }

        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}