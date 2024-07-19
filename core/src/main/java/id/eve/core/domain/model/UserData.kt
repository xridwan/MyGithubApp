package id.eve.core.domain.model

import android.os.Parcelable
import id.eve.core.data.local.entity.Favorite
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val id: Int?,
    val login: String?,
    val avatarUrl: String?,
    val name: String?,
) : Parcelable

@Parcelize
data class DetailData(
    val id: Int?,
    val login: String?,
    val avatarUrl: String?,
    val name: String?,
    val followers: Int?,
    val following: Int?,
    val repository: String?,
    val company: String?,
    val location: String?,
) : Parcelable {

    companion object {
        fun mapToFavorite(detailData: DetailData?): Favorite {
            return Favorite(
                id = detailData?.id ?: 0,
                login = detailData?.login ?: "",
                avatarUrl = detailData?.avatarUrl ?: "",
                name = detailData?.name ?: ""
            )
        }
    }
}

@Parcelize
data class FollowerData(
    val id: Int?,
    val loginId: String?,
    val login: String?,
    val avatarUrl: String?,
    val name: String?,
) : Parcelable

@Parcelize
data class FollowingData(
    val id: Int?,
    val loginId: String?,
    val login: String?,
    val avatarUrl: String?,
    val name: String?,
) : Parcelable