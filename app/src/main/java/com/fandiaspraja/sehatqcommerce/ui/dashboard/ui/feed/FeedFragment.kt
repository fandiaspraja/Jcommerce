package com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.feed

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.fandiaspraja.sehatqcommerce.R
import com.fandiaspraja.sehatqcommerce.core.data.Resource
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product
import com.fandiaspraja.sehatqcommerce.core.ui.ProductAdapter
import com.fandiaspraja.sehatqcommerce.databinding.FragmentFeedBinding
import com.fandiaspraja.sehatqcommerce.ui.detailproduct.DetailProductActivity
import kotlinx.android.synthetic.main.fragment_feed.*
import org.koin.android.viewmodel.ext.android.viewModel


class FeedFragment : Fragment() {

    private val feedViewModel: FeedViewModel by viewModel()

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    lateinit var productAdapter: ProductAdapter

    var filterProduct : ArrayList<Product> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {


            productAdapter = ProductAdapter()

            productAdapter.onItemClick = {selectedData ->
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            feedViewModel.product.observe(viewLifecycleOwner, {product ->
                if (product != null) {
                    when (product) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            for (item in 0 until product.data!!.size){
                                filterProduct.add(product.data[item])
                            }
                            productAdapter.setData(product.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = product.message ?: getString(R.string.something_wrong)
                        }
                    }
                }


            })


            search_prod.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    filter(query)
                    return false
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    filter(newText)
                    return false
                }
            })
            with(binding.rvSearch) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = productAdapter
            }
        }

    }

     fun filter(text: String) {



        val filteredlist: ArrayList<Product> = ArrayList()

         filteredlist.clear()

        for (item in filterProduct.indices) {
            if (filterProduct[item].title.toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(filterProduct[item])
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {

            productAdapter.filterList(filteredlist)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}