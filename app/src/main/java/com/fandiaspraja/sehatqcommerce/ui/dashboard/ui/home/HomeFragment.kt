package com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fandiaspraja.sehatqcommerce.R
import com.fandiaspraja.sehatqcommerce.core.data.Resource
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product
import com.fandiaspraja.sehatqcommerce.core.ui.CategoryAdapter
import com.fandiaspraja.sehatqcommerce.core.ui.ProductAdapter
import com.fandiaspraja.sehatqcommerce.databinding.FragmentHomeBinding
import com.fandiaspraja.sehatqcommerce.ui.detailproduct.DetailProductActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var productAdapter: ProductAdapter

    var filterProduct : ArrayList<Product> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val categoryAdapter = CategoryAdapter()
            categoryAdapter.onItemClick = { selectedData ->
//                val intent = Intent(activity, DetailTourismActivity::class.java)
//                intent.putExtra(DetailTourismActivity.EXTRA_DATA, selectedData)
//                startActivity(intent)
            }

            productAdapter = ProductAdapter()
            productAdapter.onItemClick = {selectedData ->
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.product.observe(viewLifecycleOwner, {product ->
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

            homeViewModel.category.observe(viewLifecycleOwner, { category ->
                if (category != null) {
                    when (category) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            categoryAdapter.setData(category.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = category.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvCategory) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = categoryAdapter
            }

            with(binding.rvProduct) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = productAdapter
            }
        }

        search_product.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filter(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })
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