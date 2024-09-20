package com.example.holyflix.ui.moviepage
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.ui.adapter.MovieAdapter
import com.example.holyflix.BuildConfig
import com.example.holyflix.R
import com.example.holyflix.adapter.LoadingStateMovieAdapter
import com.example.holyflix.databinding.FragmentMoviePageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviePageFragment : Fragment(R.layout.fragment_movie_page) {

    private val binding by viewBinding(FragmentMoviePageBinding::bind)
    private  lateinit var  movieAdapter : MovieAdapter
    private val viewModel: MoviePageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeDiscoverMovie()
    }

    private fun setMovieAdapter() = with(binding){
        movieAdapter = MovieAdapter{ resultPopulerItem ->
            val idMovie = resultPopulerItem.id
            if (idMovie != null) {
                val idBundle = Bundle().apply {
                    putInt("id_movie", idMovie.toInt())

                }

                findNavController().navigate(R.id.action_moviePageFragment_to_detaiailMovieFragment,idBundle)

            }
        }
        rvMovie.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        Log.i("setMovieAdapter" , "masuk fun")
        rvMovie.adapter = movieAdapter.withLoadStateFooter(
            footer = LoadingStateMovieAdapter {movieAdapter.retry()}
        )
    }

    private fun observeDiscoverMovie() = with(binding){
        setMovieAdapter()
        viewModel.getDiscoverMovies(token = BuildConfig.TOKEN_KEY)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.movieDiscover.collectLatest {movieAdapter.submitData(it)}
            }
        }
    }

    override fun onDestroyView() {
        binding.rvMovie.adapter = null
        super.onDestroyView()
    }
}



