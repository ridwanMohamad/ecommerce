package com.mridwan.ecommerce.view.ui.search_product

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mridwan.ecommerce.R
import com.mridwan.ecommerce.model.response.Product
import com.mridwan.ecommerce.view.ui.detail_product.DetailProductActivity
import com.mridwan.ecommerce.view.ui.home.ListProductViewHolder

class ListSearchAdapter(private val context: Context) :
    RecyclerView.Adapter<ListSearchViewHolder>(){
    var repoProductSearch:List<Product> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSearchViewHolder {
        return ListSearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_search,parent,false), context)
    }

    override fun onBindViewHolder(holder: ListSearchViewHolder, position: Int) {
        holder.bind(repoProductSearch[position])

    }

    fun updateRepoList(repoList: List<Product>) {
        this.repoProductSearch = repoList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return repoProductSearch.size
    }

}

class ListSearchViewHolder(private val view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
    fun bind(product:Product) {
        val image = itemView.findViewById<ImageView>(R.id.image)
        val title = itemView.findViewById<TextView>(R.id.title)
        val price = itemView.findViewById<TextView>(R.id.price)
        Glide.with(context).load(product.imageUrl).into(image)
        title.text = product.title
        price.text = product.price
        itemView.setOnClickListener {
            val intent = Intent(context, DetailProductActivity::class.java)
            intent.putExtra("title", product.title)
            intent.putExtra("image", product.imageUrl)
            intent.putExtra("description", product.description)
            intent.putExtra("price", product.price)
            intent.putExtra("loved", product.loved)
            context.startActivity(intent)
        }
    }
}
