package com.example.umesh_yadav_25327.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umesh_yadav_25327.R
import com.example.umesh_yadav_25327.adapters.BrandAdapter
import com.example.umesh_yadav_25327.adapters.CategoryAdapter
import com.example.umesh_yadav_25327.adapters.TrendingAdapter
import com.example.umesh_yadav_25327.models.BrandModel
import com.example.umesh_yadav_25327.models.CategoryModel
import com.example.umesh_yadav_25327.models.TrendingModel
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    var scrollView: ScrollView? = null
    var progressBar: ProgressBar? = null
    var trendRec: RecyclerView? = null
    var brandRec: RecyclerView? = null
    var categoryRec: RecyclerView? = null
    var db: FirebaseFirestore? = null
    var trendingModelList: MutableList<TrendingModel>? = null
    var trendingAdapter: TrendingAdapter? = null
    var brandModelList: MutableList<BrandModel>? = null
    var brandAdapter: BrandAdapter? = null
    var categoryModelList: MutableList<CategoryModel>? = null
    var categoryAdapter: CategoryAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        db = FirebaseFirestore.getInstance()
        trendRec = root.findViewById(R.id.trend_recycleView)
        brandRec = root.findViewById(R.id.brands_recycleView)
        categoryRec = root.findViewById(R.id.cat_recycleView)
        progressBar = root.findViewById(R.id.progressBar)
        scrollView = root.findViewById(R.id.scroll_view)
        progressBar?.setVisibility(View.VISIBLE)
        scrollView?.setVisibility(View.GONE)
        trendRec?.setLayoutManager(LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false))
        trendingModelList = ArrayList()
        trendingAdapter = TrendingAdapter(requireActivity(),
            trendingModelList as ArrayList<TrendingModel>
        )
        trendRec?.setAdapter(trendingAdapter)
        db!!.collection("TrendingProducts")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val trendingModel = document.toObject(
                            TrendingModel::class.java
                        )
                        trendingModelList?.add(trendingModel)
                        trendingAdapter!!.notifyDataSetChanged()
                        progressBar?.setVisibility(View.GONE)
                        scrollView?.setVisibility(View.VISIBLE)
                    }
                } else {
                    Toast.makeText(activity, "Error: " + task.exception, Toast.LENGTH_SHORT).show()
                }
            }
        categoryRec?.setLayoutManager(LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false))
        categoryModelList = ArrayList()
        categoryAdapter = CategoryAdapter(requireActivity(),
            categoryModelList as ArrayList<CategoryModel>
        )
        categoryRec?.setAdapter(categoryAdapter)
        db!!.collection("Categories")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val categoryModel = document.toObject(
                            CategoryModel::class.java
                        )
                        categoryModelList?.add(categoryModel)
                        categoryAdapter!!.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(activity, "Error: " + task.exception, Toast.LENGTH_SHORT).show()
                }
            }
        brandRec?.setLayoutManager(LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false))
        brandModelList = ArrayList()
        brandAdapter = BrandAdapter(requireActivity(), brandModelList as ArrayList<BrandModel>)
        brandRec?.setAdapter(brandAdapter)
        db!!.collection("Brands")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val brandModel = document.toObject(BrandModel::class.java)
                        brandModelList?.add(brandModel)
                        brandAdapter!!.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(activity, "Error: " + task.exception, Toast.LENGTH_SHORT).show()
                }
            }
        return root
    }
}