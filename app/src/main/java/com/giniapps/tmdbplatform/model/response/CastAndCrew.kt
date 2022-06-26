package com.giniapps.tmdbplatform.model.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


interface Credit : Parcelable {
    fun getImageUrl(): String?

    val creditId: Any
    val credit: String
    val name: String
    val profilePath: String?

    companion object {
        private const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}

@Parcelize
@Entity(tableName = "CASTS")
data class Cast(
    @SerializedName("character")
    override val credit: String,
    override val name: String,
    @SerializedName("profile_path")
    override val profilePath: String?,
    @PrimaryKey
    override val creditId: Int
) : Credit {
    override fun getImageUrl() =
        TmdbItem.TMDB_IMAGE_URL + profilePath
}

@Parcelize
data class Crew(
    @SerializedName("job")
    override val credit: String,
    override val name: String,
    @SerializedName("profile_path")
    override val profilePath: String?,
    override val creditId: String
) : Credit{
    override fun getImageUrl() =
        TmdbItem.TMDB_IMAGE_URL + profilePath

}
