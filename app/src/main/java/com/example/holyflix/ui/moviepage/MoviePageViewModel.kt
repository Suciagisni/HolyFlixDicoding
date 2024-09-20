package com.example.holyflix.ui.moviepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.data.remote.response.movie.ResultsItem
import com.example.core.data.remote.response.populer.ResultsPopulerItem
import com.example.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviePageViewModel @Inject constructor(
   private val movieUseCase: MovieUseCase): ViewModel() {

    private val _movieDiscover = MutableStateFlow<PagingData<ResultsPopulerItem>>(PagingData.empty())
    val movieDiscover : MutableStateFlow<PagingData<ResultsPopulerItem>> = _movieDiscover
//    val movie = movieUseCase.getPopularMovie().asLiveData()


    fun getDiscoverMovies(token: String)=
        viewModelScope.launch {
        movieUseCase.getPopulerMovie(token).cachedIn(viewModelScope)
            .collectLatest {
                _movieDiscover.value = it

            }

    }
}