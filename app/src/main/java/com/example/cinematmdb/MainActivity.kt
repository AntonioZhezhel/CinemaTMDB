package com.example.cinematmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.cinematmdb.favorites.FavoriteMoviesFragment
import com.example.cinematmdb.popular.PopularMoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {

            R.id.action_popular -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PopularMoviesFragment(), "popular")
                    .commitNow()
                title = getString(R.string.popular)
            }

            R.id.action_favorites -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FavoriteMoviesFragment(), "favorites")
                    .commitNow()
                title = getString(R.string.my_favorites)
            }


        }

        return true
    }
}
