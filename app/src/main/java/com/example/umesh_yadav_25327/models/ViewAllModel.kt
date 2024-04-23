package com.example.umesh_yadav_25327.models

import java.io.Serializable

class ViewAllModel : Serializable {

    var name: String? = null
    var description: String? = null
    var type: String? = null
    var image_url: String? = null
    var rating: String? = null
    var price: String? = null

    constructor()

    constructor(
        name: String?,
        description: String?,
        type: String?,
        imageUrl: String?,
        rating: String?,
        price: String?
    ) {
        this.name = name
        this.description = description
        this.type = type
        this.image_url = imageUrl
        this.rating = rating
        this.price = price
    }
}

