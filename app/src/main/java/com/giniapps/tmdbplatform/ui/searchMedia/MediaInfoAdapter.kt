package com.giniapps.tmdbplatform.ui.searchMedia

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.model.response.TmdbItem
import javax.inject.Inject

class MediaInfoAdapter @Inject constructor() : RecyclerView.Adapter<MediaInfoAdapter.ViewHolder>() {
    private var mediaList = emptyList<TmdbItem>()
    lateinit var onItemClickListener: OnItemClickListener

    fun interface OnItemClickListener {
        fun callback(item: TmdbItem)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_info)
        val mediaNameTV: TextView = itemView.findViewById(R.id.mediaNameTV)
        val ratingTV: TextView = itemView.findViewById(R.id.ratingTV)
        val dateTV: TextView = itemView.findViewById(R.id.dateTV)


        init {
            itemView.setOnClickListener {
                val item = getItem(adapterPosition)

                onItemClickListener.callback(item)
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.media_info_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.imageView)
            .load(Uri.parse(mediaList[position].getImageUrl()))
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)

        holder.mediaNameTV.text = mediaList[position].name
        holder.ratingTV.text = "${mediaList[position].voteAverage}"
        holder.dateTV.text =
            mediaList[position].releaseDate?.dropLast(6) ?: mediaList[position].releaseDate

    }

    fun getItem(position: Int): TmdbItem = mediaList[position]

    override fun getItemCount(): Int = mediaList.size

    fun setData(newMediaList: List<TmdbItem>) {
        val diffutil = MyDiffUtil(mediaList, newMediaList)
        val diffResult = DiffUtil.calculateDiff(diffutil)
        mediaList = newMediaList
        diffResult.dispatchUpdatesTo(this)
    }
}