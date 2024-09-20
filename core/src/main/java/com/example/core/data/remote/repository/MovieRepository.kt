package com.example.core.data.remote.repository

import androidx.paging.PagingData
import com.example.core.data.remote.dataSource.LocalDataSource
import com.example.core.data.remote.dataSource.RemoteDataSource
import com.example.core.data.remote.response.detailpopuler.DetailPopulerResponse
import com.example.core.data.remote.response.populer.ResultsPopulerItem
import com.example.core.data.resource.ApiSourceResponse
import com.example.core.data.resource.NetworkBoundResource
import com.example.core.data.resource.Resource
import com.example.core.domain.model.DetailPopulerMovie
import com.example.core.domain.model.Populer
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {

    override suspend fun moviePopuler(token: String):
            Flow<PagingData<ResultsPopulerItem>> = remoteDataSource.getPopulerMovie(token)

    override suspend fun getDetailPopulerMovie(
        idMovie: Int,
        token: String
    ): Flow<Resource<DetailPopulerMovie>> = object : NetworkBoundResource<DetailPopulerMovie, DetailPopulerResponse>(){
        override fun loadFromNetwork(data: DetailPopulerResponse): Flow<DetailPopulerMovie> =
            DataMapper.mapDetailPopulerResponseToDomain(data)

        override suspend fun createCall(): Flow<ApiSourceResponse<DetailPopulerResponse>> =
            remoteDataSource.getDetailPopulerMovie(idMovie,token)

    }.asFlow()

    override fun getMoviesFavorite(): Flow<List<Populer>> =
        localDataSource.getMoviesFavorite().map {
            DataMapper.mapListEntityFavToDomain(it)
        }


    override fun getMovieFavorite(id: Int): Flow<Boolean> =
        localDataSource.getMovieFavoriteById(id)


    override suspend fun insertMovie(populer: Populer) =
        localDataSource.insertMovie(DataMapper.mapPopulerDomainToEntity(populer))

    override suspend fun deleteMovie(populer: Populer) =
        localDataSource.deleteMovie(DataMapper.mapPopulerDomainToEntity(populer))
}