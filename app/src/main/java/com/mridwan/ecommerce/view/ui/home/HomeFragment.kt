package com.mridwan.ecommerce.view.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mridwan.ecommerce.R
import com.mridwan.ecommerce.databinding.FragmentHomeBinding
import com.mridwan.ecommerce.view.ui.search_product.SearchActivity


class HomeFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentHomeBinding
    private lateinit var adapterProduct: ListProductAdapter
    private lateinit var adapterCategory: ListCategoryAdapter
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentHomeBinding.inflate(inflater,container,false).apply {
            homeViewModel = ViewModelProviders.of(this@HomeFragment).get(HomeViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.homeViewModel?.fetchRepoList()
        productRecyclerView = view.findViewById(R.id.repo_list_rv)
        categoryRecyclerView = view.findViewById(R.id.category_rv)
        val search = view.findViewById<EditText>(R.id.search_box)
        search.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val intent = Intent(context, SearchActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0);
                true
            }
            false
        })

        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.homeViewModel
        if (viewModel != null){
            adapterProduct = ListProductAdapter(viewDataBinding.homeViewModel!!, this.requireContext())
            val layoutManagerProduct = LinearLayoutManager(activity)
            productRecyclerView.layoutManager = layoutManagerProduct
            productRecyclerView.adapter = adapterProduct

            val layoutManagerCategory = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            adapterCategory = ListCategoryAdapter(viewDataBinding.homeViewModel!!, this.requireContext())
            categoryRecyclerView.layoutManager = layoutManagerCategory
            categoryRecyclerView.adapter = adapterCategory
        }
    }

    private fun setupObservers() {
        viewDataBinding.homeViewModel?.product?.observe(viewLifecycleOwner, Observer {
            adapterProduct.updateRepoList(it)
        })
        viewDataBinding.homeViewModel?.categoryData?.observe(viewLifecycleOwner, Observer {
            adapterCategory.updateRepoList(it)
        })
    }
}