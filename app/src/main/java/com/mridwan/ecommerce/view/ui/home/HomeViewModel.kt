package com.mridwan.ecommerce.view.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mridwan.ecommerce.model.request.RepositoryData
import com.mridwan.ecommerce.model.response.Category
import com.mridwan.ecommerce.model.response.Data
import com.mridwan.ecommerce.model.response.Product
import com.mridwan.ecommerce.view.base.BaseViewModel

class HomeViewModel: BaseViewModel() {
    val categoryData = MutableLiveData<List<Category>>()
    val product = MutableLiveData<List<Product>>()

    fun fetchRepoList() {
        dataLoading.value = true
        RepositoryData.getInstance().getRepoData() { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                categoryData.value = response?.category
                product.value = response?.productPromo
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}