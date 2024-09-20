package com.example.core.utils

import com.example.core.data.local.entities.MovieEntity
import com.example.core.data.remote.response.detailpopuler.DetailPopulerResponse
import com.example.core.domain.model.DetailPopulerMovie
import com.example.core.domain.model.Populer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {

    fun mapDetailPopulerResponseToDomain(movieDetailResponse: DetailPopulerResponse)
            : Flow<DetailPopulerMovie> = flowOf(
        DetailPopulerMovie(
            id = movieDetailResponse.id!!,
            backdropPath = movieDetailResponse.backdropPath!!,
            originalTitle = movieDetailResponse.originalTitle!!,
            title = movieDetailResponse.title!!,
            popularity = movieDetailResponse.popularity!!.toString(),
            overview = movieDetailResponse.overview!!,
            posterPath = movieDetailResponse.posterPath!!,
            releaseDate = movieDetailResponse.releaseDate!!,
            voteAverage = movieDetailResponse.voteAverage!!.toString(),
            homepage = movieDetailResponse.homepage!!
        )
    )

    fun mapDetailPopulerDomaintoPopulerDomain(detailPopulerMovie: DetailPopulerMovie) : Populer =
        Populer(
            id = detailPopulerMovie.id,
            posterPath = detailPopulerMovie.posterPath,
            title = detailPopulerMovie.title,
            originalTitle = detailPopulerMovie.originalTitle,
            overview = detailPopulerMovie.overview,
            popularity = detailPopulerMovie.popularity,
            releaseDate = detailPopulerMovie.releaseDate
        )

    fun mapPopulerDomainToEntity(populer: Populer)
            : MovieEntity =
        MovieEntity(
            id = populer.id,
            posterPath = populer.posterPath,
            title = populer.title,
            originalTitle = populer.originalTitle,
            overview = populer.overview,
            popularity = populer.popularity,
            releaseDate = populer.releaseDate
        )

    fun mapListEntityFavToDomain(movie: List<MovieEntity>) : List<Populer> {
        val listFavMovie = ArrayList<Populer>()
        movie.map {
            val populer = Populer(
                id = it.id,
                posterPath = it.posterPath,
                title = it.title,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                releaseDate = it.releaseDate
            )
            listFavMovie.add(populer)
        }
        return listFavMovie
    }
}





//    fun mapResponseToEntities(input: List<ResultMovie>): List<MovieEntity> {
//        val listMovie = ArrayList<MovieEntity>()
//        input.map {
//            val movie = MovieEntity(
//                it.id,
//                it.adult,
//                ConstantValue.PATH_IMAGE + it.backdropPath,
//                it.originalLanguage,
//                it.originalTitle,
//                it.overview,
//                it.popularity,
//                ConstantValue.PATH_IMAGE + it.posterPath,
//                it.releaseDate,
//                it.title,
//                it.video,
//                it.voteAverage,
//                it.voteCount
//            )
//            listMovie.add(movie)
//        }
//        return listMovie
//    }
//
//    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
//        val listMovie = ArrayList<Movie>()
//        input.map {
//            val movie = Movie(
//                it.id,
//                it.adult,
//                it.backdropPath,
//                it.originalLanguage,
//                it.originalTitle,
//                it.overview,
//                it.popularity,
//                it.posterPath,
//                it.releaseDate,
//                it.title,
//                it.video,
//                it.voteAverage,
//                it.voteCount
//            )
//            listMovie.add(movie)
//        }
//        return listMovie
//    }
//
//    fun mapResponseToDomain(input: List<ResultMovie>): Flow<List<Movie>> {
//        val listMovie = ArrayList<Movie>()
//        input.map {
//            val movie = Movie(
//                it.id,
//                it.adult,
//                ConstantValue.PATH_IMAGE + it.backdropPath,
//                it.originalLanguage,
//                it.originalTitle,
//                it.overview,
//                it.popularity,
//                ConstantValue.PATH_IMAGE + it.posterPath,
//                it.releaseDate,
//                it.title,
//                it.video,
//                it.voteAverage,
//                it.voteCount
//            )
//            listMovie.add(movie)
//        }
//        return flowOf(listMovie)
//    }
//
//    fun mapDomainToEntities(input: Movie): MovieEntity {
//        val movie = MovieEntity(
//            input.id,
//            input.adult,
//            input.backdropPath,
//            input.originalLanguage,
//            input.originalTitle,
//            input.overview,
//            input.popularity,
//            input.posterPath,
//            input.releaseDate,
//            input.title,
//            input.video,
//            input.voteAverage,
//            input.voteCount
//        )
//
//        return movie
//    }
//
//    fun mapResponseToDomainDetail(input: DetailMovieResponse): Flow<DetailMovie> {
//        return flowOf(
//            DetailMovie(
//                input.id,
//                input.adult,
//                ConstantValue.PATH_IMAGE + input.backdropPath,
//                input.budget,
//                input.genres.map { Genre(it.id, it.name) },
//                input.homepage,
//                input.imdbId,
//                input.originCountry,
//                input.originalLanguage,
//                input.originalTitle,
//                input.overview,
//                input.popularity,
//                ConstantValue.PATH_IMAGE + input.posterPath,
//                input.releaseDate,
//                input.revenue,
//                input.runtime,
//                input.status,
//                input.tagline,
//                input.title,
//                input.video,
//                input.voteAverage,
//                input.voteCount
//            )
//        )
//    }

//    fun mapDetailToMovie(input: DetailMovie) : Movie {
//        return Movie(
//            input.id,
//            input.adult,
//            input.backdropPath,
//            input.originalLanguage,
//            input.originalTitle,
//            input.overview,
//            input.popularity,
//            input.posterPath,
//            input.releaseDate,
//            input.title,
//            input.video,
//            input.voteAverage,
//            input.voteCount
//        )
//    }