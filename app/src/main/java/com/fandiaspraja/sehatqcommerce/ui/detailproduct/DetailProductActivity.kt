package com.fandiaspraja.sehatqcommerce.ui.detailproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fandiaspraja.sehatqcommerce.R
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product
import com.fandiaspraja.sehatqcommerce.databinding.ActivityDetailProductBinding
import kotlinx.android.synthetic.main.activity_detail_product.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailProductActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailTourismViewModel: DetailProductViewModel by viewModel()
    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val detailProduct = intent.getParcelableExtra<Product>(EXTRA_DATA)
        showDetailProduct(detailProduct)
    }

    private fun showDetailProduct(detailProduct: Product?) {
        detailProduct?.let {
            supportActionBar?.title = detailProduct.title
            binding.content.tvDetailDescription.text = detailProduct.description
            Glide.with(this)
                .load(detailProduct.imageUrl)
                .into(binding.ivDetailImage)

            var statusFavorite = detailProduct.isFavorite
            var statusBuy = detailProduct.isBuy

            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailTourismViewModel.setFavoriteProduct(detailProduct, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
            binding.btnBuy.setOnClickListener {
                statusBuy = !statusBuy
                detailTourismViewModel.setBuyProduct(detailProduct, statusBuy)
                setStatusBuy(statusBuy)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fav_fill))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fav_border))
        }
    }

    private fun setStatusBuy(statusBuy: Boolean) {
        if (statusBuy) {
            binding.btnBuy.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
        } else {
            binding.btnBuy.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        }
    }
}