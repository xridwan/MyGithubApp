package id.eve.core.domain.repository

import android.database.Cursor
import id.eve.core.data.local.entity.Favorite
import id.eve.core.data.Resource
import id.eve.core.domain.model.DetailData
import id.eve.core.domain.model.FollowerData
import id.eve.core.domain.model.FollowingData
import id.eve.core.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(username: String): Flow<Resource<List<UserData>>>
    fun getUser(username: String): Flow<Resource<DetailData>>
    fun getFollowers(username: String): Flow<Resource<List<FollowerData>>>
    fun getFollowing(username: String): Flow<Resource<List<FollowingData>>>
    fun getFavorite(): Flow<List<UserData>>
    suspend fun addFavorite(favorite: Favorite)
    suspend fun removeFavorite(id: Int)
    suspend fun checkFavorite(id: String): Int
    fun getData(): Cursor
}