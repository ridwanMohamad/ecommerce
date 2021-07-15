package com.mridwan.ecommerce.model

class ProductHistory {
    var id: Int = 0
    var title: String? = null
    var description: String? = null
    var price: String? = null
    var imageLink: String? = null
    var loved: Int = 0

    constructor(
        id: Int,
        title: String?,
        description: String?,
        price: String?,
        imageLink: String?,
        loved: Int
    ) {
        this.id = id
        this.title = title
        this.description = description
        this.price = price
        this.imageLink = imageLink
        this.loved = loved
    }

    constructor(
        title: String?,
        description: String?,
        price: String?,
        imageLink: String?,
        loved: Int
    ) {
        this.title = title
        this.description = description
        this.price = price
        this.imageLink = imageLink
        this.loved = loved
    }


}