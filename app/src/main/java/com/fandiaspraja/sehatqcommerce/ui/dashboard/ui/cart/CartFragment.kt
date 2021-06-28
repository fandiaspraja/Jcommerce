package com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.fandiaspraja.sehatqcommerce.R
import com.fandiaspraja.sehatqcommerce.core.data.Resource
import com.fandiaspraja.sehatqcommerce.core.ui.PurchaseHistoryAdapter
import com.fandiaspraja.sehatqcommerce.databinding.FragmentCartBinding
import com.fandiaspraja.sehatqcommerce.ui.detailproduct.DetailProductActivity
import org.koin.android.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    private val chartViewModel: CartViewModel by viewModel()

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val purchaseHistoryAdapter = PurchaseHistoryAdapter()
            purchaseHistoryAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            chartViewModel.product.observe(viewLifecycleOwner, {product ->
//                if (product != null) {
//                    when (product) {
//                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
//                        is Resource.Success -> {
//                            binding.progressBar.visibility = View.GONE
//                            purchaseHistoryAdapter.setData(product.data)
//                        }
//                        is Resource.Error -> {
//                            binding.progressBar.visibility = View.GONE
//                            binding.viewError.root.visibility = View.VISIBLE
//                            binding.viewError.tvError.text = product.message ?: getString(R.string.something_wrong)
//                        }
//                    }
//                }

                purchaseHistoryAdapter.setData(product)
                binding.viewEmpty.root.visibility = if (product.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvPurchase) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = purchaseHistoryAdapter
            }
        }
    }
}