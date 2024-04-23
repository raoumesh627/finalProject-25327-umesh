package com.example.umesh_yadav_25327.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.umesh_yadav_25327.R
import com.example.umesh_yadav_25327.activities.DetailActivity
import com.example.umesh_yadav_25327.models.ViewAllModel

class ViewAllAdapter(private val context: Context, private val list: List<ViewAllModel>) :
    RecyclerView.Adapter<ViewAllAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_all_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        Glide.with(context).load(currentItem.image_url).into(holder.img)
        holder.name.text = currentItem.name
        holder.description.text = currentItem.description
        holder.price.text = currentItem.price
        holder.rating.text = currentItem.rating

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("detail", currentItem)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.view_name)
        var description: TextView = itemView.findViewById(R.id.view_desc)
        var price: TextView = itemView.findViewById(R.id.view_price)
        var rating: TextView = itemView.findViewById(R.id.view_rating)
        var img: ImageView = itemView.findViewById(R.id.view_img)
    }
}
