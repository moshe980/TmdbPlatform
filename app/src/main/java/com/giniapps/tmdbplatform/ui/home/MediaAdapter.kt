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
import com.giniapps.tmdbplatform.model.response.Media
import com.giniapps.tmdbplatform.ui.searchMedia.MyDiffUtil
import javax.inject.Inject

class MediaAdapter @Inject constructor() : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {
    @Inject
    lateinit var diffutil: MyDiffUtil


    private var list = emptyList<Media>()
    lateinit var onChildItemClickListener: OnChildItemClickListener

    fun interface OnChildItemClickListener {
        fun childCallback(item: Media)
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


    fun getItem(position: Int): Media = list[position%list.size]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.media_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newPosition=position%list.size
        Glide.with(holder.imageView)
            .load(Uri.parse(list[newPosition].getImageUrl()))
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    fun setData(newMediaList: List<Media>) {
        diffutil = MyDiffUtil(list, newMediaList)
        val diffResult = DiffUtil.calculateDiff(diffutil)
        list = newMediaList
        diffResult.dispatchUpdatesTo(this)
    }
}