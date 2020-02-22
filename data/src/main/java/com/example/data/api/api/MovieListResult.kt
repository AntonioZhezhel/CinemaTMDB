package com.example.data.api.api

import com.example.data.api.entities.MovieData
import com.google.gson.annotations.SerializedName

class MovieListResult {
    var page: Int = 0
    @SerializedName("results")
    lateinit var movies: List<MovieData>
}