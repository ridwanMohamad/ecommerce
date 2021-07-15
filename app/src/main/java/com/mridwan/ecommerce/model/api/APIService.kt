package com.mridwan.ecommerce.model.api

import com.mridwan.ecommerce.model.response.APIResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("home")
    fun getRepo(): Call<List<APIResponse>>
}