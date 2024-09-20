package com.example.core.ui.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.core.data.remote.response.movie.ResultsItem
import com.example.core.data.remote.response.populer.ResultsPopulerItem
import com.example.core.databinding.MoviesItemBinding

class MovieAdapter(var onClickItem: ((ResultsPopulerItem) -> Unit?)? = null) :
    PagingDataAdapter<ResultsPopulerItem, MovieAdapter.ViewHolder>(callback){


    companion object {
        val callback = object : DiffUtil.ItemCallback<ResultsPopulerItem>() {
            override fun areItemsTheSame(
                oldItem: ResultsPopulerItem,
                newItem: ResultsPopulerItem
            ):
                    Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(
                oldItem: ResultsPopulerItem,
                newItem: ResultsPopulerItem
            ): Boolean =
                oldItem == newItem
        }
    }


    class ViewHolder(private val binding:MoviesItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: ResultsPopulerItem) = with(binding){
            tvTitle.text = item.title.toString()
            tvOverview.text = item.overview.toString()
            Log.i("MovieAdapter" ,"http://image.tmdb.org/t/p/w500${item.posterPath}")
            Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w500${item.posterPath}").listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmerPoster.visibility = GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmerPoster.visibility = GONE
                        return false
                    }
                }
            ).into(imgPoster)

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("onBindViewHolder", "holder = ${holder}")
        val data = getItem(position)
        if (data != null) holder.bind(data)  else Log.d("onBindViewHolder", "data = null")

        holder.itemView.setOnClickListener {
            if (data != null) {
                onClickItem?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
}