package com.example.umesh_yadav_25327.models

class UserModel {
    @JvmField
    var name: String? = null
    @JvmField
    var email: String? = null
    var password: String? = null
    var number: String? = null
    var address: String? = null
    @JvmField
    var profileImg: String? = null

    constructor()
    constructor(name: String?, email: String?, password: String?) {
        this.name = name
        this.email = email
        this.password = password
    }
}
