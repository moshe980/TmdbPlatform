package com.giniapps.tmdbplatform.model.response

import com.google.gson.annotations.SerializedName

data class GenreWrapper(
    val genres: List<Genre>,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("status_code")
    val statusCode: Int?,
    val success: Boolean?,
)