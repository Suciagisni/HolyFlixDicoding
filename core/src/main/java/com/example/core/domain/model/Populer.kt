package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Populer(

    val id:Int,
    val posterPath: String,
    val title: String,
    val overview: String,
    val originalTitle: String,
    val popularity: String,
    val releaseDate: String,

): Parcelable
