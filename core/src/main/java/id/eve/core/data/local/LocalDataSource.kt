package id.eve.core.data.local

import android.database.Cursor
import id.eve.core.data.local.entity.DetailEntity
import id.eve.core.data.local.entity.Favorite
import id.eve.core.data.local.entity.FollowerEntity
import id.eve.core.data.local.entity.FollowingEntity
import id.eve.core.data.local.entity.UserEntity
import id.eve.core.data.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val userDao: UserDao,
) {
    fun getUsers(): Flow<List<UserEntity>> = userDao.getUsers()

    suspend fun clearUsers() = userDao.clearUsers()

    suspend fun insertUserList(userList: List<UserEntity>) = userDao.insertUserList(userList)

    fun getUser(username: String): Flow<DetailEntity?> = userDao.getUser(username)

    suspend fun insertUser(detailEntity: DetailEntity) = userDao.insertUser(detailEntity)

    fun getFollower(loginId: String): Flow<List<FollowerEntity>> = userDao.getFollower(loginId)

    suspend fun clearFollower() = userDao.clearFollower()

    suspend fun insertFollowerList(followers: List<FollowerEntity>) =
        userDao.insertFollowerList(followers)

    fun getFollowing(loginId: String): Flow<List<FollowingEntity>> = userDao.getFollowing(loginId)

    suspend fun clearFollowing() = userDao.clearFollowing()

    suspend fun insertFollowingList(followers: List<FollowingEntity>) =
        userDao.insertFollowingList(followers)

    fun getFavorite(): Flow<List<Favorite>> = userDao.getFavorite()

    suspend fun addFavorite(favorite: Favorite) = userDao.addFavorite(favorite)

    suspend fun removeFavorite(id: Int) = userDao.removeFavorite(id)

    suspend fun checkFavorite(id: String): Int = userDao.checkFavorite(id)

    fun getData(): Cursor = userDao.getData()
}