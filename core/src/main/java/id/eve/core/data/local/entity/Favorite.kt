package id.eve.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.eve.core.domain.model.UserData
import java.io.Serializable

@Entity(tableName = "favorite_tb")
data class Favorite(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val name: String,
) : Serializable {

    companion object {
        fun mapFavoriteToDomain(input: List<Favorite>): List<UserData> =
            input.map {
                UserData(
                    id = it.id,
                    login = it.login,
                    avatarUrl = it.avatarUrl,
                    name = it.name
                )
            }
    }
}
