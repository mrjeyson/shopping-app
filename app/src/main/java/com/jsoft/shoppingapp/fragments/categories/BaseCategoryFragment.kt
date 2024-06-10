package com.jsoft.shoppingapp.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jsoft.shoppingapp.R
import com.jsoft.shoppingapp.adapters.BestProductsAdapter
import com.jsoft.shoppingapp.databinding.FragmentBaseCategoryBinding
import com.jsoft.shoppingapp.utils.showBottomNavigationView

open class BaseCategoryFragment : Fragment(R.layout.fragment_base_category) {

    private lateinit var binding: FragmentBaseCategoryBinding
    protected val offerAdapter: BestProductsAdapter by lazy { BestProductsAdapter() }
    protected val bestProductsAdapter: BestProductsAdapter by lazy { BestProductsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOfferRv()
        setupBestProductsRv()

        bestProductsAdapter.onClick = {
            val b = Bundle().apply { putParcelable("product", it) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment, b)
        }

        offerAdapter.onClick = {
            val b = Bundle().apply { putParcelable("product", it) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment, b)
        }

        binding.rvBestProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollHorizontally(1) && dx != 0) {
                    onOfferPagingRequest()
                }
            }
        })

        binding.nestedScrollBaseCategory.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollV, _, _ ->
            if (v.getChildAt(0).bottom <= v.height + scrollV) {
                onBestProductPagingRequest()
            }
        })
    }

    fun showOfferLoading() {
        binding.offerProductsProgressbar.visibility = View.VISIBLE
    }

    fun hideOfferLoading() {
        binding.offerProductsProgressbar.visibility = View.GONE

    }

    fun showBestProductsLoading() {
        binding.bestProductsProgressbar.visibility = View.VISIBLE

    }

    fun hideBestProductsLoading() {
        binding.bestProductsProgressbar.visibility = View.GONE

    }


    open fun onOfferPagingRequest() {

    }


    open fun onBestProductPagingRequest() {

    }

    private fun setupBestProductsRv() {
        binding.rvBestProducts.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            adapter = bestProductsAdapter
        }
    }
    private fun setupOfferRv() {
        binding.rvOffer.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = offerAdapter
        }
    }
    override fun onResume() {
        super.onResume()
        showBottomNavigationView()
    }
}