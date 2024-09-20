package com.example.core.ui.adapter


import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.core.databinding.ListFavoriteMovieBinding
import com.example.core.domain.model.Populer


class FavoriteMovieAdapter(var onClickItem: ((Populer) -> Unit?)? = null)
    : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>(){

    private var diffCallbackUser = object : DiffUtil.ItemCallback<Populer>() {
        override fun areItemsTheSame(
            oldItem: Populer,
            newItem: Populer
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Populer,
            newItem: Populer
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private var differ = AsyncListDiffer(this, diffCallbackUser)

    fun submitData(valueList: ArrayList<Populer>) {
        differ.submitList(valueList)
    }

    class ViewHolder(var binding: ListFavoriteMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ListFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataMovie = differ.currentList[position]
        Log.i("onBindViewHolder", dataMovie.title)
        with(holder.binding) {
            Glide.with(holder.itemView.context).load("http://image.tmdb.org/t/p/w500${dataMovie.posterPath}").listener(
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            shimmerPosterFav.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            shimmerPosterFav.visibility = View.GONE
                            return false
                        }

                    }
                )
                .into(imgPosterFavorite)
//            Log.i("onBindViewHolder", "img link = ${EndPointMovie.IMAGE_BASE_URL}${dataMovie.posterPath}")
//            popularity.text= dataMovie.popularity
            tvTittleFav.text = dataMovie.title
            tvDateFav.text = dataMovie.releaseDate
            tvOriginTitleFav.text = dataMovie.originalTitle
        }

        holder.itemView.setOnClickListener {
            onClickItem?.invoke(dataMovie)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}