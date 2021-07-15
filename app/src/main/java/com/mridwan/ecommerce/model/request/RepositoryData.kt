package com.mridwan.ecommerce.model.request

import android.util.Log
import com.mridwan.ecommerce.model.api.APIClient
import com.mridwan.ecommerce.model.response.APIResponse
import com.mridwan.ecommerce.model.response.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryData {
    fun getRepoData(onResult: (isSuccess: Boolean, response: Data?) -> Unit) {
        APIClient.instance.getRepo().enqueue(object : Callback<List<APIResponse>> {
            override fun onResponse(call: Call<List<APIResponse>>?, response: Response<List<APIResponse>>?) {
                val rs = response?.body()
                if (response != null) {
                    if (response.isSuccessful){
                        onResult(true, response.body()?.get(0)?.data)
                    } else {
                        onResult(false,null)
                    }
                } else {
                    onResult(false,null)
                }
            }

            override fun onFailure(call: Call<List<APIResponse>>, t: Throwable) {
                Log.d("FAILURE", t.message.toString())
                onResult(false,null)
            }
        })
    }

    companion object {
        private var INSTANCE: RepositoryData? = null
        fun getInstance() = INSTANCE?:RepositoryData().also {
            INSTANCE = it
        }
    }
}