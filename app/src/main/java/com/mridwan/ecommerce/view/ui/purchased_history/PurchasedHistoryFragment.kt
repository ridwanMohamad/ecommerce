package com.mridwan.ecommerce.view.ui.purchased_history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mridwan.ecommerce.R
import com.mridwan.ecommerce.databinding.FragmentPurchasedHistoryBinding
import com.mridwan.ecommerce.view.ui.home.ListProductAdapter
import com.mridwan.ecommerce.view.ui.search_product.ListSearchAdapter

class PurchasedHistoryFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentPurchasedHistoryBinding
    private lateinit var adapterProduct: ListPurchasedHistoryAdapter
    private lateinit var productRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentPurchasedHistoryBinding.inflate(inflater,container,false).apply {
            purchasedViewModel = ViewModelProviders.of(this@PurchasedHistoryFragment).get(PurchasedHistoryViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.purchasedViewModel?.fetchRepoList(requireContext())
        productRecyclerView = view.findViewById(R.id.purchased_rv)
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.purchasedViewModel?.product?.observe(viewLifecycleOwner, Observer{
            Log.d("TAG", "setupObservers: "+it)
            adapterProduct.updateRepoList(it)
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.purchasedViewModel
        if (viewModel!=null){
            adapterProduct = ListPurchasedHistoryAdapter(viewDataBinding.purchasedViewModel!!,this.requireContext())
            val layoutManager = LinearLayoutManager(activity)
            productRecyclerView.layoutManager = layoutManager
            productRecyclerView.adapter = adapterProduct
        }
    }
}