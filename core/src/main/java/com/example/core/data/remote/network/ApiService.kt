package com.example.core.data.remote.network

import com.example.core.data.remote.response.detailpopuler.DetailPopulerResponse
import com.example.core.data.remote.response.movie.MovieResponse
import com.example.core.data.remote.response.populer.PopulerResponse
import com.example.core.utils.ConstantValue
import com.example.core.utils.ConstantValue.DETAIL_POPULER
import com.example.core.utils.ConstantValue.POPULER
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceMovie {

    @GET("movie")
    suspend fun discoverMovie(
        @Header("Authorization") token : String,
        @Query("include_adult") adultStatus : Boolean,
        @Query("include_video") videoStatus : Boolean,
        @Query("language") language : String,
        @Query("page") page : Int,
        @Query("sort_by") sortBy : String
    ) : MovieResponse

    @GET(POPULER)
    suspend fun populerMovie(
        @Query("api_key") apiKey : String
    ) : PopulerResponse

    @GET(DETAIL_POPULER)
    suspend fun populerDetailMovie(
        @Path("movie_id") idMovie : Int,
        @Query("api_key") apiKey : String
    ) : DetailPopulerResponse

//    @GET(ConstantValue.PATH_POPULAR_MOVIE)
//    suspend fun getListPopular(
//    ) : ListMovieResponse
//
//    @GET(ConstantValue.PATH_DETAIL_MOVIE)
//    suspend fun getDetail(
//        @Path("movie_id") movieId : Int
//    ) : DetailMovieResponse
}


