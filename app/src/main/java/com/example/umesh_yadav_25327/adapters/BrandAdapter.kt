package com.example.umesh_yadav_25327.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.umesh_yadav_25327.R
import com.example.umesh_yadav_25327.models.BrandModel

class BrandAdapter(private val context: Context, private val brandModelList: List<BrandModel>) :
    RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.brand_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(brandModelList[position].getImageURL()).into(holder.brandImage)
    }

    override fun getItemCount(): Int {
        return brandModelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var brandImage: ImageView

        init {
            brandImage = itemView.findViewById(R.id.brand_img)
        }
    }
}