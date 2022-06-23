package com.giniapps.tmdbplatform.ui.media

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.model.response.Cast
import javax.inject.Inject

class CastsAdapter @Inject constructor() : RecyclerView.Adapter<CastsAdapter.ViewHolder>() {
    private var list = emptyList<Cast>()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.personImg)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.imageView)
            .load(Uri.parse(list[position].getImageUrl()))
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)


    }

    fun getItem(position: Int): Cast = list[position]

    override fun getItemCount(): Int = list.size

    fun setData(newMediaList: List<Cast>) {
        val diffutil = CastDiffUtil(list, newMediaList)
        val diffResult = DiffUtil.calculateDiff(diffutil)
        list = newMediaList
        diffResult.dispatchUpdatesTo(this)
    }
}