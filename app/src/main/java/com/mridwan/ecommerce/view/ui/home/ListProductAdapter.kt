package com.mridwan.ecommerce.view.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mridwan.ecommerce.R
import com.mridwan.ecommerce.databinding.ItemListProductBinding
import com.mridwan.ecommerce.model.response.Product
import com.mridwan.ecommerce.view.ui.detail_product.DetailProductActivity

class ListProductAdapter(private val homeViewModel: HomeViewModel, private val context: Context) :
RecyclerView.Adapter<ListProductViewHolder>(){
    var repoProduct:List<Product> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemListProductBinding.inflate(inflater,parent,false)
        return ListProductViewHolder(dataBinding, homeViewModel, context)
    }

    override fun onBindViewHolder(holder: ListProductViewHolder, position: Int) {
        holder.setup(repoProduct[position])
    }
    fun updateRepoList(repoList: List<Product>) {
        this.repoProduct = repoList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return repoProduct.size
    }

}

class ListProductViewHolder (
    private val dataBinding: ViewDataBinding,
    private val homeViewModel: HomeViewModel,
    private val context: Context) : RecyclerView.ViewHolder(dataBinding.root){
    val tumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
    val fav = itemView.findViewById<ToggleButton>(R.id.fav)
    val title = itemView.findViewById<TextView>(R.id.title)
    val price = itemView.findViewById<TextView>(R.id.price)

    fun setup(item: Product){
        title.setText(item.title)
        price.setText(item.price)
        Glide.with(context).load(item.imageUrl).into(tumbnail)
        if (item.loved == 1){
            fav.isChecked = true
        }
        itemView.setOnClickListener {
            val intent = Intent(context,DetailProductActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("image", item.imageUrl)
            intent.putExtra("description", item.description)
            intent.putExtra("price", item.price)
            intent.putExtra("loved", item.loved)
            context.startActivity(intent)
        }
    }
}
