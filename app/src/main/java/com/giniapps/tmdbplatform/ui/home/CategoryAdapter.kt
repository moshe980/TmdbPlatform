package com.giniapps.tmdbplatform.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.model.response.MediaCategory
import com.giniapps.tmdbplatform.model.response.TmdbItem
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import javax.inject.Inject

class CategoryAdapter @Inject constructor() : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var list = emptyList<MediaCategory>()
    private var viewPool = RecyclerView.RecycledViewPool()

    lateinit var onParentItemClickListener: OnParentItemClickListener

    fun interface OnParentItemClickListener {
        fun parentCallback(item: TmdbItem)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val listNameTV: TextView = itemView.findViewById(R.id.listNameTV)
        val categoryRecyclerview: CarouselRecyclerview =
            itemView.findViewById(R.id.carousel_recyclerview)



    }
    fun getList()=list
    fun getItem(position: Int): MediaCategory = list[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mediaAdapter = MediaAdapter()
        holder.listNameTV.text = list[position].name
        holder.categoryRecyclerview.set3DItem(true)
        holder.categoryRecyclerview.setAlpha(true)
        mediaAdapter.setData(list[position].list)
        holder.categoryRecyclerview.adapter = mediaAdapter
        holder.categoryRecyclerview.setRecycledViewPool(viewPool)
        mediaAdapter.onChildItemClickListener = MediaAdapter.OnChildItemClickListener{
            onParentItemClickListener.parentCallback(it)
        }


    }

    override fun getItemCount(): Int = list.size

    fun setData(newCategoryList: List<MediaCategory>) {
        val diffutil = CategoryDiffUtil(list, newCategoryList)
        val diffResult = DiffUtil.calculateDiff(diffutil)
        list = newCategoryList
        diffResult.dispatchUpdatesTo(this)
    }

    fun getViewPool() = viewPool


}