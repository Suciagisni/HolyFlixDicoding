package com.example.core.data.remote.dataSource

import com.ashoka.core.data.local.room.FavoriteMovieDAO
import com.example.core.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor (
    private val dao: FavoriteMovieDAO) {

    fun getMoviesFavorite(): Flow<List<MovieEntity>> = dao.getMovies()

    fun getMovieFavoriteById(id: Int): Flow<Boolean> = dao.getMoviesById(id)

    suspend fun insertMovie(movie: MovieEntity) = dao.insertMovieFavorite(movie)

    suspend fun deleteMovie(movie: MovieEntity) = dao.deleteMovieFavorite(movie)
//
//    fun getFavoriteMovie(id: Int) : Flow<Boolean> = dao.getMoviesById(id)
//
//    suspend fun insertMovie(movie: MovieEntity) = dao.insertMovieFavorite(movie)
//
//    suspend fun deleteMovie(movie: MovieEntity) = dao.deleteMovieFavorite(movie)
}