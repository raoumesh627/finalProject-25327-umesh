package com.example.umesh_yadav_25327.models

class BrandModel {
    @kotlin.jvm.JvmField
    var imageURL: String? = null

    constructor()
    constructor(imageURL: String?) {
        this.imageURL = imageURL
    }
    fun getImageURL(): String? {
        return imageURL
    }
}
