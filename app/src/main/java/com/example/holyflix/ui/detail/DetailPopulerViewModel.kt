package com.example.holyflix.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.resource.Resource
import com.example.core.domain.model.DetailPopulerMovie
import com.example.core.domain.model.Populer
import com.example.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPopulerViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    private var _detailPopulerMovie = MutableStateFlow<Resource<DetailPopulerMovie>?>(null)
    val detailPopulerMovie : MutableStateFlow<Resource<DetailPopulerMovie>?> = _detailPopulerMovie

    fun getDetailPopulerMovie(
        idMovie : Int,
        token :String
    ) = viewModelScope.launch {
        _detailPopulerMovie.value = Resource.Loading()
        useCase.getDetailPopulerMovie(idMovie, token).collectLatest {
            _detailPopulerMovie.value = it
        }
    }

    fun getFavMovieById(id : Int) : LiveData<Boolean> {
        Log.d("DetailMoviesViewModel", "getFavMovieById")
        return useCase.getMovieFavoriteById(id).asLiveData()
    }


    fun insertFavMovie(populer: Populer) = viewModelScope.launch {
        Log.d("DetailMoviesViewModel", "INSERT. ${populer.title}")
        useCase.insertMovie(populer)
    }

    fun deleteFavMovie(populer: Populer) = viewModelScope.launch {
        Log.d("DetailMoviesViewModel", "masuk DELETE")
        useCase.deleteMovie(populer)
    }

}