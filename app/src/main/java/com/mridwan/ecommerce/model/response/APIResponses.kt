package com.mridwan.ecommerce.model.response

data class APIResponse (
    val data: Data,
)

data class Data(
    val category: List<Category>,
    val productPromo: List<Product>,
)

data class Category (
    val id:Int,
    val imageUrl:String,
    val name:String
)

data class Product (
    val id:Int,
    val imageUrl:String,
    val title:String,
    val description:String,
    val price:String,
    val loved:Int
)
