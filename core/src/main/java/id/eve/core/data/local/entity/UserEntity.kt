package id.eve.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.eve.core.domain.model.DetailData
import id.eve.core.domain.model.FollowerData
import id.eve.core.domain.model.FollowingData
import id.eve.core.domain.model.UserData
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "search")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "login") val login: String?,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "name") val name: String?,
) : Parcelable {

    companion object {
        fun mapUserEntitiesToDomain(input: List<UserEntity>): List<UserData> =
            input.map {
                UserData(
                    id = it.id,
                    login = it.login,
                    avatarUrl = it.avatarUrl,
                    name = it.name,
                )
            }
    }
}

@Parcelize
@Entity(tableName = "detail")
data class DetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "login") val login: String?,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "followers") val followers: Int?,
    @ColumnInfo(name = "following") val following: Int?,
    @ColumnInfo(name = "repository") val repository: String?,
    @ColumnInfo(name = "company") val company: String?,
    @ColumnInfo(name = "location") val location: String?,
) : Parcelable {

    companion object {
        fun mapDetailEntitiesToDomain(input: DetailEntity?): DetailData =
            DetailData(
                id = input?.id,
                login = input?.login,
                avatarUrl = input?.avatarUrl,
                name = input?.name,
                followers = input?.followers,
                following = input?.following,
                repository = input?.repository,
                company = input?.company,
                location = input?.location,
            )
    }
}

@Parcelize
@Entity(tableName = "follower")
data class FollowerEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "loginId") val loginId: String?,
    @ColumnInfo(name = "login") val login: String?,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "name") val name: String?,
) : Parcelable {

    companion object {
        fun mapFollowerEntitiesToDomain(input: List<FollowerEntity>): List<FollowerData> =
            input.map {
                FollowerData(
                    id = it.id,
                    loginId = it.loginId,
                    login = it.login,
                    avatarUrl = it.avatarUrl,
                    name = it.name,
                )
            }
    }
}

@Parcelize
@Entity(tableName = "following")
data class FollowingEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "loginId") val loginId: String?,
    @ColumnInfo(name = "login") val login: String?,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "name") val name: String?,
) : Parcelable {

    companion object {
        fun mapFollowingEntitiesToDomain(input: List<FollowingEntity>): List<FollowingData> =
            input.map {
                FollowingData(
                    id = it.id,
                    loginId = it.loginId,
                    login = it.login,
                    avatarUrl = it.avatarUrl,
                    name = it.name,
                )
            }
    }
}
