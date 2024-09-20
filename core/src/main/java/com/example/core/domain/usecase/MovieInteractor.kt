package com.example.core.domain.usecase

import androidx.paging.PagingData
import com.example.core.data.remote.response.populer.ResultsPopulerItem
import com.example.core.data.resource.Resource
import com.example.core.domain.model.DetailPopulerMovie
import com.example.core.domain.model.Populer
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val repository: IMovieRepository) : MovieUseCase {


    override suspend fun getPopulerMovie(token: String):
            Flow<PagingData<ResultsPopulerItem>> = repository.moviePopuler(token)

    override suspend fun getDetailPopulerMovie(
        idMovie: Int,
        token: String
    ): Flow<Resource<DetailPopulerMovie>>  =
        repository.getDetailPopulerMovie(idMovie, token)

    override fun getMoviesFavorite(): Flow<List<Populer>> = repository.getMoviesFavorite()


    override fun getMovieFavoriteById(id: Int): Flow<Boolean> =
        repository.getMovieFavorite(id)

    override suspend fun insertMovie(populer: Populer) = repository.insertMovie(populer)

    override suspend fun deleteMovie(populer: Populer) = repository.deleteMovie(populer)


}