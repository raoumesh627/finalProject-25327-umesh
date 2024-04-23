package com.example.umesh_yadav_25327.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umesh_yadav_25327.R
import com.example.umesh_yadav_25327.adapters.MyOrderAdapter
import com.example.umesh_yadav_25327.models.MyOrderModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyOrdersFragment : Fragment() {
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null
    var recyclerView: RecyclerView? = null
    var orderAdapter: MyOrderAdapter? = null
    var orderModelList: MutableList<MyOrderModel?>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_my_orders, container, false)

        // Set the toolbar title
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("My Orders")
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        recyclerView = root.findViewById(R.id.orderrecView)
        recyclerView?.setLayoutManager(LinearLayoutManager(activity))
        orderModelList = ArrayList()
        orderAdapter = MyOrderAdapter(requireActivity(), orderModelList)
        recyclerView?.setAdapter(orderAdapter)
        firestore!!.collection("CurrentUser").document(auth!!.currentUser!!.uid)
            .collection("MyOrder").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (documentSnapshot in task.result.documents) {
                        val documentId = documentSnapshot.id
                        val orderModel = documentSnapshot.toObject(
                            MyOrderModel::class.java
                        )
                        orderModel!!.documentId = documentId
                        orderModelList?.add(orderModel)
                        orderAdapter!!.notifyDataSetChanged()
                    }
                }
            }
        return root
    }
}
