package com.mridwan.ecommerce.view.ui.purchased_history

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mridwan.ecommerce.model.request.RepositoryData
import com.mridwan.ecommerce.model.response.Product
import com.mridwan.ecommerce.view.base.BaseViewModel
import com.mridwan.ecommerce.view.utils.DBHelper

class PurchasedHistoryViewModel: BaseViewModel() {
    var product = MutableLiveData<List<Product>>()
    fun fetchRepoList(context: Context) {
        dataLoading.value = true
        val dbHandler = DBHelper(context, null)

        product.value=dbHandler.getAllProduct()
        Log.d("TAG", "fetchRepoList: "+product.value)

    }
}