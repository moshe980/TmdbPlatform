package com.giniapps.tmdbplatform.ui.searchMedia

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.databinding.MediaInfoItemBinding
import com.giniapps.tmdbplatform.model.response.Media
import com.giniapps.tmdbplatform.ui.media.CastDiffUtil
import javax.inject.Inject

class MediaInfoAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Media, MediaInfoAdapter.MediaViewHolder>(MEDIA_COMPARATOR) {
    private var list = emptyList<Media>()

    lateinit var onItemClickListener: AdapterView.OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding =
            MediaInfoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class MediaViewHolder(private val binding: MediaInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.callback(item)

                    }
                }
            }

        }

        fun bind(media: Media) {
            binding.apply {
                Glide.with(binding.imageInfo)
                    .load(Uri.parse(media.getImageUrl()))
                    .fitCenter()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.imageInfo)

                mediaNameTV.text = media.name
                ratingTV.text = "${media.voteAverage}"
                dateTV.text =
                    media.releaseDate?.dropLast(6) ?: media.releaseDate
            }


        }

    }

    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun callback(item: Media)

    }

    companion object {
        private val MEDIA_COMPARATOR = object : DiffUtil.ItemCallback<Media>() {
            override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean =
                when {
                    oldItem.id != newItem.id -> {
                        false
                    }
                    oldItem.name != newItem.name -> {
                        false
                    }
                    oldItem.backdropPath != newItem.backdropPath -> {
                        false
                    }
                    oldItem.overview != newItem.overview -> {
                        false
                    }
                    oldItem.releaseDate != newItem.releaseDate -> {
                        false
                    }
                    oldItem.voteAverage != newItem.voteAverage -> {
                        false
                    }
                    oldItem.posterPath != newItem.posterPath -> {
                        false
                    }

                    else -> true


                }
        }

    }
}
