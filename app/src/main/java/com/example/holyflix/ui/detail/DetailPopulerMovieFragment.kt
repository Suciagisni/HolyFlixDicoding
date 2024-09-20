package com.example.holyflix.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.core.data.resource.Resource
import com.example.core.domain.model.Populer
import com.example.core.utils.DataMapper
import com.example.holyflix.BuildConfig
import com.example.holyflix.R
import com.example.holyflix.databinding.FragmentDetaiailMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.microedition.khronos.opengles.GL
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailPopulerMovieFragment : Fragment(R.layout.fragment_detaiail_movie) {

    private val binding by viewBinding(FragmentDetaiailMovieBinding::bind)
    private val viewModel : DetailPopulerViewModel by viewModels()
    private var isFavorite by Delegates.notNull<Boolean>()
    private var populer : Populer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFavMovie()
        setFavorite()
        onBackPressed()
    }


    private fun initFavMovie() {
        val idMovie = arguments?.getInt("id_movie")
        if (idMovie != null  ){
            viewModel.getFavMovieById(idMovie).observe(viewLifecycleOwner){
                isFavorite = it
                setButtonFavorite(it)

            }
            observeDetailPopuler(idMovie)
        }
    }


    private fun setFavorite() = with(binding){
        btnFavorite.setOnClickListener {
            isFavorite = if (!isFavorite){
                populer?.let {populer ->
                    viewModel.insertFavMovie(populer = populer)
                }
                setButtonFavorite(false)
                false
            } else{
                populer?.let {populer ->
                    viewModel.deleteFavMovie(populer = populer)
                    setButtonFavorite(true)
                }
                true
            }
        }
    }

    private fun setButtonFavorite(flag : Boolean) = with(binding){
        if (flag){
            btnFavorite.setIconResource(R.drawable.baseline_favorite_24)
        } else {
            btnFavorite.setIconResource(R.drawable.baseline_favorite_border_24)
        }
    }

    private fun setFavoriteMovie () = with(binding) {

    }

    @SuppressLint("CheckResult")
    private fun observeDetailPopuler (idMovie : Int) = with (binding){
        viewModel.getDetailPopulerMovie(
            idMovie = idMovie,
            token = BuildConfig.TOKEN_KEY )

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.detailPopulerMovie.collectLatest {
                    it?.let { response ->
                        when(response) {
                            is Resource.Error ->{

                            }
                            is Resource.Loading ->{

                            }
                            is Resource.Success ->{
                                response.data?.let { detailPopulerMovie ->
                                    populer = DataMapper.mapDetailPopulerDomaintoPopulerDomain(detailPopulerMovie = detailPopulerMovie)
                                    Glide.with(requireContext()).load("http://image.tmdb.org/t/p/w500${detailPopulerMovie.backdropPath}").into(imgBanner)
                                    tvTitle.text = detailPopulerMovie.title
                                    tvOverview.text = detailPopulerMovie.overview
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onBackPressed() = with(binding){
        topAppBar.setOnClickListener {
            findNavController().navigateUp()
        }
    }



    override fun onStop() {
        super.onStop()
    }
}