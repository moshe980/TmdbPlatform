package com.giniapps.tmdbplatform.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.model.response.TmdbItem
import com.giniapps.tmdbplatform.ui.searchMedia.MyDiffUtil
import javax.inject.Inject

class MediaAdapter @Inject constructor() : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {
    @Inject
    lateinit var diffutil: MyDiffUtil

    private var list = emptyList<TmdbItem>()
    lateinit var onChildItemClickListener: OnChildItemClickListener

    fun interface OnChildItemClickListener {
        fun childCallback(item: TmdbItem)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)

        init {
            itemView.setOnClickListener {
                val item = getItem(adapterPosition)

                onChildItemClickListener.childCallback(item)
            }

        }
    }


    fun getItem(position: Int): TmdbItem = list[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.media_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.imageView)
            .load(Uri.parse(list[position].getImageUrl()))
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int = list.size

    fun setData(newMediaList: List<TmdbItem>) {
        diffutil = MyDiffUtil(list, newMediaList)
        val diffResult = DiffUtil.calculateDiff(diffutil)
        list = newMediaList
        diffResult.dispatchUpdatesTo(this)
    }
}