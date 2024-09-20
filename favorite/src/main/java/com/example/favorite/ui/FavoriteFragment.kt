package com.example.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.domain.model.Populer
import com.example.core.ui.adapter.FavoriteMovieAdapter
import com.example.favorite.R
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.DaggerFavoriteComponent
import com.example.holyflix.di.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel : FavoriteViewModel by viewModels { factory }
    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private lateinit var  favMovieAdapter : FavoriteMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFavMovieAdapter()
        observeFavMovie()
    }



    private fun setFavMovieAdapter() = with(binding.rvFavMovie) {
        favMovieAdapter = FavoriteMovieAdapter{
            toDetailFrag(it)
        }
        layoutManager = LinearLayoutManager(requireContext())
        adapter = favMovieAdapter

    }

    private fun observeFavMovie(){
        viewModel.favMovie.observe(viewLifecycleOwner){movie ->
            favMovieAdapter.submitData(movie as ArrayList<Populer>)

        }
    }



    private fun toDetailFrag(populer: Populer){
        val idBundle = Bundle().apply {
            putInt("id_movie", populer.id)
        }
        findNavController().navigate(com.example.holyflix.R.id.action_favoriteFragment_to_detailMoviesFragment,idBundle)
    }

    override fun onDestroyView() {
        binding.rvFavMovie.adapter = null
        super.onDestroyView()
    }
}