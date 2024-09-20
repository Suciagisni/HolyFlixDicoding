package com.example.core.data.remote.dataSource

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.remote.network.ApiResponse
import com.example.core.data.remote.network.ApiServiceMovie
import com.example.core.data.remote.paging.MoviePopulerPagingSource
import com.example.core.data.remote.response.detailpopuler.DetailPopulerResponse
import com.example.core.data.remote.response.populer.ResultsPopulerItem
import com.example.core.data.resource.ApiSourceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiServiceMovie) {

    suspend fun getDetailPopulerMovie(
        idMovie : Int,
        token: String,
    ): Flow<ApiSourceResponse.Success<DetailPopulerResponse>> = flow {
        try {
            val response = apiService.populerDetailMovie(idMovie,token)
            emit(ApiSourceResponse.Success(response))
        } catch (e : Exception) {
            e.printStackTrace()
            Log.e("REMOTE_DATA_SOURCE", e.toString())
        }
    }.flowOn(Dispatchers.IO)

//    suspend fun getListMovie() : Flow<ApiResponse<List<ResultMovie>>> {
//        return flow {
//            try {
//                val response = apiService.getListPopular()
//                val result = response.results
//                if (result.isNotEmpty()){
//                    emit(ApiResponse.Success(response.results))
//                } else {
//                    emit(ApiResponse.Empty)
//                }
//            } catch (e : Exception){
//                emit(ApiResponse.Error(e.toString()))
//                Log.e("RemoteDataSource", e.toString())
//            }
//        }.flowOn(Dispatchers.IO)
//    }
//
//    suspend fun getDetailMovie(id: Int) : Flow<ApiResponse<DetailMovieResponse>> {
//        return flow {
//            try {
//                val response = apiService.getDetail(id)
//                emit(ApiResponse.Success(response))
//            } catch (e: Exception) {
//                emit(ApiResponse.Error(e.toString()))
//                Log.e("RemoteDataSource", e.toString())
//            }
//        }.flowOn(Dispatchers.IO)
//    }

    suspend fun getPopulerMovie (
        token: String,
    ) : Flow<PagingData<ResultsPopulerItem>> = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false),
        pagingSourceFactory = {
            Log.i("remote getDiscoverMovie", "masuk")
            MoviePopulerPagingSource(apiService,token) }
    ).flow
}