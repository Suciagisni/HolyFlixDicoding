package com.example.core.domain.usecase

import androidx.paging.PagingData
import com.example.core.data.remote.response.populer.ResultsPopulerItem
import com.example.core.data.resource.Resource
import com.example.core.domain.model.DetailPopulerMovie
import com.example.core.domain.model.Populer
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    suspend fun getPopulerMovie(
        token: String,
    ): Flow<PagingData<ResultsPopulerItem>>

    suspend fun getDetailPopulerMovie(
        idMovie: Int,
        token: String
    ) : Flow<Resource<DetailPopulerMovie>>


    fun getMoviesFavorite(): Flow<List<Populer>>
    fun getMovieFavoriteById(id: Int) : Flow<Boolean>
    suspend fun insertMovie(populer: Populer)
    suspend fun deleteMovie(populer: Populer)
}