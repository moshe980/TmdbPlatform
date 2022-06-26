package com.giniapps.tmdbplatform.model.response

import com.google.gson.annotations.SerializedName

//Movie AND TV SHOW
data class ItemWrapper<T : TmdbItem>(
    @SerializedName("results")
    val items: List<T>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val pages: Int
)

data class VideoWrapper(
    @SerializedName("results")
    val videos: List<Video>
)

data class CreditWrapper(
    val cast: List<Cast>,
    val crew: List<Crew>
)
