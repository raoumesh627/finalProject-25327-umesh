package com.example.umesh_yadav_25327.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.umesh_yadav_25327.R
import com.example.umesh_yadav_25327.models.TrendingModel

class TrendingAdapter(
    private val context: Context,
    private val trendingModelList: List<TrendingModel>
) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.trending_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(trendingModelList[position].imageURL).into(holder.popImage)
        holder.name.text = trendingModelList[position].name
        holder.description.text = trendingModelList[position].description
        holder.rating.text = trendingModelList[position].rating
    }

    override fun getItemCount(): Int {
        return trendingModelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var popImage: ImageView
        var name: TextView
        var description: TextView
        var rating: TextView

        init {
            popImage = itemView.findViewById(R.id.trend_img)
            name = itemView.findViewById(R.id.trend_name)
            description = itemView.findViewById(R.id.trend_des)
            rating = itemView.findViewById(R.id.trend_rating)
        }
    }
}
