package com.fandiaspraja.sehatqcommerce.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fandiaspraja.sehatqcommerce.R
import com.fandiaspraja.sehatqcommerce.core.domain.model.Category
import com.fandiaspraja.sehatqcommerce.databinding.ItemCategoryBinding
import java.util.ArrayList

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {

    private var listData = ArrayList<Category>()
    var onItemClick: ((Category) -> Unit)? = null

    fun setData(newListData: List<Category>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCategoryBinding.bind(itemView)
        fun bind(data: Category) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .into(imgCategory)
                categoryName.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}