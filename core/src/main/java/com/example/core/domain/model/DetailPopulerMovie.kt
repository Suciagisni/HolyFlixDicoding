package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailPopulerMovie(
    val id:Int,
    val backdropPath: String,
    val originalTitle: String,
    val title: String,
    val popularity: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: String,
    val homepage: String,
) : Parcelable
