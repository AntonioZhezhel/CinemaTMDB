package com.example.cinematmdb.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematmdb.R
import com.example.cinematmdb.common.ImageLoader
import com.example.cinematmdb.entities.Movie
import kotlinx.android.synthetic.main.favorite_movies_adapter_row.view.*

class FavoriteMoviesAdapter constructor(private val imageLoader: ImageLoader,
                                        private val onMovieSelected: (Movie, View) -> Unit) : RecyclerView.Adapter<FavoriteMoviesAdapter.MovieCellViewHolder>() {

    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(
            R.layout.favorite_movies_adapter_row,
            parent,
            false)
        return MovieCellViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        val movie = movies[position]
        holder?.bind(movie, imageLoader, onMovieSelected)
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, imageLoader: ImageLoader, listener: (Movie, View) -> Unit) = with(itemView) {
            title.text = movie.originalTitle
            movie.posterPath?.let { imageLoader.load(it, image) }

            movie.overview?.let {
                overview.text = movie.overview
                overview.visibility = android.view.View.VISIBLE
            } ?: run {
                overview.visibility = android.view.View.GONE
            }
            setOnClickListener { listener(movie, itemView) }
        }

    }
}