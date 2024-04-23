package com.example.umesh_yadav_25327.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umesh_yadav_25327.R
import com.example.umesh_yadav_25327.activities.PlacedOrderActivity
import com.example.umesh_yadav_25327.adapters.MyCartAdapter
import com.example.umesh_yadav_25327.models.MyCartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable

class MyCartFragment : Fragment() {
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null
    var recyclerView: RecyclerView? = null
    var cartAdapter: MyCartAdapter? = null
    var cartModelList: MutableList<MyCartModel?>? = null
    var overTotalAmount: TextView? = null
    var buyNow: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_my_cart, container, false)

        // Set the toolbar title
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("My Cart")

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        recyclerView = root.findViewById(R.id.recView)
        buyNow = root.findViewById(R.id.buy)
        recyclerView?.setLayoutManager(LinearLayoutManager(activity))
        overTotalAmount = root.findViewById(R.id.textView6)
        cartModelList = ArrayList()
        cartAdapter = MyCartAdapter(requireActivity(), cartModelList)
        recyclerView?.setAdapter(cartAdapter)
        firestore!!.collection("CurrentUser").document(auth!!.currentUser!!.uid)
            .collection("AddToCart").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (documentSnapshot in task.result.documents) {
                        val documentId = documentSnapshot.id
                        val cartModel = documentSnapshot.toObject(
                            MyCartModel::class.java
                        )
                        cartModel!!.documentId = documentId
                        cartModelList?.add(cartModel)
                        cartAdapter!!.notifyDataSetChanged()
                    }
                    calculateTotalAmount(cartModelList as ArrayList<MyCartModel?>)
                }
            }
        buyNow?.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, PlacedOrderActivity::class.java)
            intent.putExtra("itemList", cartModelList as Serializable?)
            startActivity(intent)
        })
        return root
    }

    private fun calculateTotalAmount(cartModelList: List<MyCartModel?>) {
        var totalAmount = 0.0
        for (cartModel in cartModelList) {
            totalAmount += cartModel!!.totalPrice.toDouble()
        }
        overTotalAmount!!.text = "Total Amount: $$totalAmount"
    }
}