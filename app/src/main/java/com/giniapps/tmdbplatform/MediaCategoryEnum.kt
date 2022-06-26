package com.giniapps.tmdbplatform

import android.content.res.Resources

enum class MediaCategoryEnum(val value: String) {
    POPULAR_MOVIES("Popular Movies:"),
    LATEST_MOVIES("Up coming Movies:"),
    TOP_RATED_MOVIES("Top rated Movies:"),

    POPULAR_TV_SHOWS("Popular Tv shows:"),
    LATEST_TV_SHOWS("Up coming Tv shows:"),
    TOP_RATED_TV_SHOWS("Top rated Tv shows:"),
}