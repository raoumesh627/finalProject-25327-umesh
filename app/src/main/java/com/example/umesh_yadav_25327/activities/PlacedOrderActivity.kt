package com.example.umesh_yadav_25327.activities;

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umesh_yadav_25327.R
import com.example.umesh_yadav_25327.models.MyCartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PlacedOrderActivity : AppCompatActivity() {
        var firestore: FirebaseFirestore? = null
        var auth: FirebaseAuth? = null
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placed_order)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val list: List<MyCartModel>? =
        intent.getSerializableExtra("itemList") as ArrayList<MyCartModel>?
        if (list != null && list.size > 0) {
        for (model in list) {
        val cartMap = HashMap<String, Any?>()
        cartMap["productName"] = model.productName
        cartMap["productPrice"] = model.productPrice
        cartMap["currentDate"] = model.currentDate
        cartMap["currentTime"] = model.currentTime
        cartMap["totalQuantity"] = model.totalQuantity
        cartMap["totalPrice"] = model.totalPrice
        firestore!!.collection("CurrentUser").document(auth!!.currentUser!!.uid)
        .collection("MyOrder").add(cartMap).addOnCompleteListener {
        Toast.makeText(
        this@PlacedOrderActivity,
        "Order Placed Successfully.",
        Toast.LENGTH_SHORT
        ).show()
        }
        }
        }
        }
        }