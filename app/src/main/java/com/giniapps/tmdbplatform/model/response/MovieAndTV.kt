package com.giniapps.tmdbplatform.model.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.giniapps.tmdbplatform.model.response.TmdbItem.Companion.TMDB_IMAGE_URL
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

interface TmdbItem : Parcelable {
    fun getImageUrl(): String?

    val id: Long
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double

    companion object {
        const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}

@Parcelize
@Entity(tableName = "MOVIES")
data class Movie(
    @PrimaryKey
    override val id: Long,
    override val overview: String,
    @SerializedName("release_date")
    override val releaseDate: String?,
    @SerializedName("poster_path")
    override val posterPath: String?,
    @SerializedName("backdrop_path")
    override val backdropPath: String?,
    @SerializedName("title")
    override val name: String,
    @SerializedName("vote_average")
    override val voteAverage: Double,

    ) : TmdbItem {
    override fun getImageUrl() =
        TMDB_IMAGE_URL + posterPath

    @Ignore
    @IgnoredOnParcel
    @SerializedName("genre_ids")
    val genreIds: List<Int> = mutableListOf()



}

@Parcelize
@Entity(tableName = "TV_SHOWS")
data class TVShow(
    @PrimaryKey
    override val id: Long,
    override val overview: String,
    @SerializedName("first_air_date")
    override val releaseDate: String?,
    @SerializedName("poster_path")
    override val posterPath: String?,
    @SerializedName("backdrop_path")
    override val backdropPath: String?,
    override val name: String,
    @SerializedName("vote_average")
    override val voteAverage: Double,

    ) : TmdbItem {
    override fun getImageUrl() = TMDB_IMAGE_URL + posterPath

    @Ignore
    @IgnoredOnParcel
    @SerializedName("genre_ids")
    val genreIds: List<Int> = mutableListOf()

}


