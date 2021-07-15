package com.mridwan.ecommerce.view.ui.search_product

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.data.DataHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mridwan.ecommerce.R
import com.mridwan.ecommerce.model.response.Product
import com.mridwan.ecommerce.view.utils.getJsonDataFromAsset
import java.util.*
import kotlin.collections.ArrayList


class SearchActivity : AppCompatActivity() {
    var displayedList:List<Product> = emptyList()
    private lateinit var adapter: ListSearchAdapter
    private lateinit var searchRv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val jsonFileString = getJsonDataFromAsset(this, "search.json")
        val gson = Gson()
        val listSearchData = object : TypeToken<List<Product>>(){}.type
        displayedList = gson.fromJson(jsonFileString,listSearchData)
        searchRv = findViewById(R.id.search_rv)
        adapter = ListSearchAdapter(this)
        val layoutManagerProduct = LinearLayoutManager(this)
        searchRv.layoutManager = layoutManagerProduct
        searchRv.adapter = adapter

        val backButton = findViewById<ImageView>(R.id.btn_back)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val textSearch = findViewById<EditText>(R.id.search_data)
        textSearch.requestFocus()
        textSearch.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("CHANGE", "afterTextChanged: "+p0.toString())
                filter(p0.toString())
            }

        })
    }
    fun filter(text: String?) {
        var temp = ArrayList<Product>()
        for (d in displayedList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.title.contains(text!!,true)) {
                temp.add(d)
            }
        }
        //update recyclerview
        if (text.isNullOrBlank()) {
            adapter.updateRepoList(emptyList())
        } else {
            adapter.updateRepoList(temp)
        }
//        disp_adapter.updateList(temp)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0);
    }
}