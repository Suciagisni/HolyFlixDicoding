package com.example.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Populer
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    val favMovie = movieUseCase.getMoviesFavorite().asLiveData()

    fun deleteMovie(populer: Populer) = viewModelScope.launch {
        movieUseCase.deleteMovie(populer)
    }


}