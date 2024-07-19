package id.eve.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.eve.core.data.local.entity.DetailEntity
import id.eve.core.data.local.entity.FollowerEntity
import id.eve.core.data.local.entity.FollowingEntity
import id.eve.core.data.local.entity.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("items")
    val items: List<UserResponse>,
) : Parcelable

@Parcelize
data class UserResponse(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("login")
    val login: String? = "",

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = "",

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("followers")
    val followers: Int? = 0,

    @field:SerializedName("following")
    val following: Int? = 0,

    @field:SerializedName("public_repos")
    val repository: String? = "",

    @field:SerializedName("company")
    val company: String? = "",

    @field:SerializedName("location")
    val location: String? = "",

    var isChecked: Boolean = false,
) : Parcelable {

    companion object {
        fun mapUserResponseToEntities(input: List<UserResponse>): List<UserEntity> {
            val userList = ArrayList<UserEntity>()
            input.map {
                val surat = UserEntity(
                    id = it.id,
                    login = it.login,
                    avatarUrl = it.avatarUrl,
                    name = it.name,
                )
                userList.add(surat)
            }
            return userList
        }

        fun mapDetailResponseToEntities(input: UserResponse): DetailEntity =
            DetailEntity(
                id = input.id,
                login = input.login,
                avatarUrl = input.avatarUrl,
                name = input.name,
                followers = input.followers,
                following = input.following,
                repository = input.repository,
                company = input.company,
                location = input.location,
            )

        fun mapFollowerResponseToEntities(
            login: String?,
            input: List<UserResponse>,
        ): List<FollowerEntity> {
            val userList = ArrayList<FollowerEntity>()
            input.map {
                val surat = FollowerEntity(
                    id = it.id,
                    loginId = login,
                    login = it.login,
                    avatarUrl = it.avatarUrl,
                    name = it.name,
                )
                userList.add(surat)
            }
            return userList
        }

        fun mapFollowingResponseToEntities(
            login: String?,
            input: List<UserResponse>,
        ): List<FollowingEntity> {
            val userList = ArrayList<FollowingEntity>()
            input.map {
                val surat = FollowingEntity(
                    id = it.id,
                    loginId = login,
                    login = it.login,
                    avatarUrl = it.avatarUrl,
                    name = it.name,
                )
                userList.add(surat)
            }
            return userList
        }
    }
}
