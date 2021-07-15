package com.mridwan.ecommerce.view.ui.purchased_history

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mridwan.ecommerce.R
import com.mridwan.ecommerce.databinding.ItemListPurchasedHistoryBinding
import com.mridwan.ecommerce.model.response.Product
import com.mridwan.ecommerce.view.ui.detail_product.DetailProductActivity
import kotlin.math.log

class ListPurchasedHistoryAdapter(private val homeViewModel: PurchasedHistoryViewModel, private val context: Context):
    RecyclerView.Adapter<ListPurchasedHistoryViewHolder>() {
    var repoPurchasedHistory:List<Product> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPurchasedHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemListPurchasedHistoryBinding.inflate(inflater,parent,false)
        return ListPurchasedHistoryViewHolder(dataBinding, homeViewModel, context)
    }

    override fun onBindViewHolder(holder: ListPurchasedHistoryViewHolder, position: Int) {
        holder.bind(repoPurchasedHistory[position])
    }

    fun updateRepoList(repoList: List<Product>) {
        Log.d("TAG", "updateRepoList: "+repoList)
        this.repoPurchasedHistory = repoList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        Log.d("TAG", "getItemCount: "+repoPurchasedHistory.size)
        return repoPurchasedHistory.size
    }
}

class ListPurchasedHistoryViewHolder(
    private val dataBinding:  ViewDataBinding,
    private val homeViewModel: PurchasedHistoryViewModel,
    private val context: Context
) : RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(product:Product) {
        Log.d("TAG", "bind: "+product.title)
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
