package com.example.cinematmdb.entities

import android.provider.MediaStore

data class MovieDetails(
    var belongsToCollection: Any? = null,
    var budget: Int? = null,
    var homepage: String? = null,
    var imdbId: String? = null,
    var overview: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var status: String? = null,
    var tagline: String? = null,
    var reviews: List<Review>? = null,
    var genres: List<String>? = null
)