package com.example.umesh_yadav_25327.activities;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umesh_yadav_25327.R
import com.example.umesh_yadav_25327.adapters.ViewAllAdapter
import com.example.umesh_yadav_25327.models.ViewAllModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ViewAllActivity : AppCompatActivity() {

private lateinit var firestore: FirebaseFirestore
private lateinit var recyclerView: RecyclerView
private lateinit var viewAllAdapter: ViewAllAdapter
private val viewAllModelList = ArrayList<ViewAllModel>()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)

        firestore = FirebaseFirestore.getInstance()
        val type = intent.getStringExtra("type")
        recyclerView = findViewById(R.id.view_all_rec)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewAllAdapter = ViewAllAdapter(this, viewAllModelList)
        recyclerView.adapter = viewAllAdapter

        // Query Firestore based on the type
        type?.let { fetchProductsByType(it) }
        }

private fun fetchProductsByType(type: String) {
        firestore.collection("AllProducts").whereEqualTo("type", type).get()
        .addOnCompleteListener { task ->
        if (task.isSuccessful) {
        for (documentSnapshot in task.result!!.documents) {
        val viewAllModel = documentSnapshot.toObject(ViewAllModel::class.java)
        viewAllModel?.let {
        viewAllModelList.add(it)
        }
        }
        viewAllAdapter.notifyDataSetChanged()
        }
        }
        }
        }
