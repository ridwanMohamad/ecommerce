package com.mridwan.ecommerce.view.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mridwan.ecommerce.R
import com.mridwan.ecommerce.databinding.ItemListCategoryBinding
import com.mridwan.ecommerce.model.response.Category
import com.mridwan.ecommerce.model.response.Product

class ListCategoryAdapter(private val homeViewModel: HomeViewModel, private val context: Context) :
    RecyclerView.Adapter<ListCategoryViewHolder>(){
    var repoCategory:List<Category> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemListCategoryBinding.inflate(inflater,parent,false)
        return ListCategoryViewHolder(dataBinding, homeViewModel, context)
    }

    override fun getItemCount(): Int {
        return repoCategory.size
    }

    override fun onBindViewHolder(holder: ListCategoryViewHolder, position: Int) {
        holder.setup(repoCategory[position])
    }

    fun updateRepoList(repoList: List<Category>) {
        this.repoCategory = repoList
        notifyDataSetChanged()
    }
}

class ListCategoryViewHolder(
    private val dataBinding: ViewDataBinding,
    private val homeViewModel: HomeViewModel,
    private val context: Context) : RecyclerView.ViewHolder(dataBinding.root) {
    val icon = itemView.findViewById<ImageView>(R.id.icon)
    val categoryName = itemView.findViewById<TextView>(R.id.category)

    fun setup(item: Category){
        categoryName.setText(item.name)
        Glide.with(context).load(item.imageUrl).into(icon)
    }
}
