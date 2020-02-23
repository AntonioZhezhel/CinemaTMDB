package com.example.cinematmdb.favorites

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.cinematmdb.R
import com.example.cinematmdb.common.App
import com.example.cinematmdb.common.BaseFragment
import com.example.cinematmdb.common.ImageLoader
import kotlinx.android.synthetic.main.fragment_favorite_movies.*
import javax.inject.Inject

class FavoriteMoviesFragment : BaseFragment() {

    @Inject
    lateinit var factory: FavoriteMoviesVMFactory
    @Inject
    lateinit var imageLoader: ImageLoader
    private lateinit var viewModel: FavoriteMoviesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyMessage: TextView
    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as App).createFavoritesComponent().inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(FavoriteMoviesViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observe(this, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun handleViewState(state: FavoritesMoviesViewState) {
        progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        emptyMessage.visibility = if (!state.isLoading && state.isEmpty) View.VISIBLE else View.GONE
        state.movies?.let { favoriteMoviesAdapter.setMovies(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteMoviesAdapter = FavoriteMoviesAdapter(imageLoader, { movie, view ->
            navigateToMovieDetailsScreen(movie, view)
        })
        recyclerView = favorite_movies_recyclerview
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = favoriteMoviesAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as App).releaseFavoritesComponent()
    }
}