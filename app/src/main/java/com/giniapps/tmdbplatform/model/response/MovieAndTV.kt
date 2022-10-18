package com.giniapps.tmdbplatform.model.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "MEDIAS")
data class Media(
    @PrimaryKey
    val id: Long,
    val overview: String,
    @SerializedName(value = "release_date", alternate = ["first_air_date"])
    val releaseDate: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName(value = "title", alternate = ["name"])
    val name: String,
    @SerializedName("vote_average")
    val voteAverage: Double,

    ) : Parcelable {
    fun getImageUrl() =
        TMDB_IMAGE_URL + posterPath

    @Ignore
    @IgnoredOnParcel
    @SerializedName("genre_ids")
    val genreIds: List<Int> = mutableListOf()

    companion object {
        const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

}




