package com.example.cinematmdb.common

import android.app.ActivityOptions
import android.os.Build
import android.util.Pair
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.cinematmdb.R
import com.example.cinematmdb.detalis.MovieDetailsActivity
import com.example.cinematmdb.entities.Movie

open class BaseFragment: Fragment() {

    protected fun navigateToMovieDetailsScreen(movie: Movie, view: View) {
        var activityOptions: ActivityOptions? = null

        val imageForTransition: View? = view.findViewById(R.id.image)
        imageForTransition?.let {
            val posterSharedElement: Pair<View, String> = Pair.create(it, getString(R.string.transition_poster))
            activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, posterSharedElement)
        }
        startActivity(
            MovieDetailsActivity.newIntent(
            context!!,
            movie.id,
            movie.posterPath), activityOptions?.toBundle())

        activity?.overridePendingTransition(0, 0)
    }
}