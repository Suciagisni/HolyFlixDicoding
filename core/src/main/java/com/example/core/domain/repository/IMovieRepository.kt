package com.example.core.domain.repository

import androidx.paging.PagingData
import com.example.core.data.remote.response.movie.ResultsItem
import com.example.core.data.remote.response.populer.ResultsPopulerItem
import com.example.core.data.resource.Resource
import com.example.core.domain.model.DetailPopulerMovie
import com.example.core.domain.model.Populer
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    suspend fun moviePopuler(
        token:String, ) : Flow<PagingData<ResultsPopulerItem>>

    suspend fun getDetailPopulerMovie(
        idMovie :Int,
        token: String
    ) : Flow<Resource<DetailPopulerMovie>>

//    fun getPopularMovie() : Flow<Resource<List<Movie>>>

//    fun getDetailMovie(id: Int) : Flow<Resource<DetailMovie>>

    fun getMoviesFavorite(): Flow<List<Populer>>
    fun getMovieFavorite(id: Int) : Flow<Boolean>
    suspend fun insertMovie(populer: Populer)
    suspend fun deleteMovie(populer: Populer)

}