package id.eve.core.data

import android.database.Cursor
import id.eve.core.data.local.LocalDataSource
import id.eve.core.data.local.entity.DetailEntity.Companion.mapDetailEntitiesToDomain
import id.eve.core.data.local.entity.Favorite
import id.eve.core.data.local.entity.FollowerEntity
import id.eve.core.data.local.entity.FollowingEntity
import id.eve.core.data.local.entity.UserEntity
import id.eve.core.data.remote.RemoteDataSource
import id.eve.core.data.remote.network.ApiResponse
import id.eve.core.data.remote.response.UserResponse
import id.eve.core.domain.model.DetailData
import id.eve.core.domain.model.FollowerData
import id.eve.core.domain.model.FollowingData
import id.eve.core.domain.model.UserData
import id.eve.core.domain.repository.UserRepository
import id.eve.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : UserRepository {
    override fun getUsers(username: String): Flow<Resource<List<UserData>>> =
        object : NetworkBoundResource<List<UserData>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<UserData>> {
                return localDataSource.getUsers().map {
                    UserEntity.mapUserEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<UserData>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getUsers(username)
            }

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = UserResponse.mapUserResponseToEntities(data)
                localDataSource.clearUsers()
                localDataSource.insertUserList(userList)
            }
        }.asFlow()

    override fun getUser(username: String): Flow<Resource<DetailData>> =
        object : NetworkBoundResource<DetailData, UserResponse>() {
            override fun loadFromDB(): Flow<DetailData> {
                return localDataSource.getUser(username).map {
                    mapDetailEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: DetailData?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<UserResponse>> {
                return remoteDataSource.getUser(username)
            }

            override suspend fun saveCallResult(data: UserResponse) {
                val detail = UserResponse.mapDetailResponseToEntities(data)
                localDataSource.insertUser(detail)
            }
        }.asFlow()

    override fun getFollowers(username: String): Flow<Resource<List<FollowerData>>> =
        object : NetworkBoundResource<List<FollowerData>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<FollowerData>> {
                return localDataSource.getFollower(username).map {
                    FollowerEntity.mapFollowerEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<FollowerData>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getFollowers(username)
            }

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = UserResponse.mapFollowerResponseToEntities(username, data)
                localDataSource.clearFollower()
                localDataSource.insertFollowerList(userList)
            }
        }.asFlow()

    override fun getFollowing(username: String): Flow<Resource<List<FollowingData>>> =
        object : NetworkBoundResource<List<FollowingData>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<FollowingData>> {
                return localDataSource.getFollowing(username).map {
                    FollowingEntity.mapFollowingEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<FollowingData>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getFollowing(username)
            }

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = UserResponse.mapFollowingResponseToEntities(username, data)
                localDataSource.clearFollowing()
                localDataSource.insertFollowingList(userList)
            }
        }.asFlow()

    override fun getFavorite(): Flow<List<UserData>> {
        return localDataSource.getFavorite().map {
            Favorite.mapFavoriteToDomain(it)
        }
    }

    override suspend fun addFavorite(favorite: Favorite) {
        localDataSource.addFavorite(favorite)
    }

    override suspend fun removeFavorite(id: Int) {
        localDataSource.removeFavorite(id)
    }

    override suspend fun checkFavorite(id: String): Int {
        return localDataSource.checkFavorite(id)
    }

    override fun getData(): Cursor {
        return localDataSource.getData()
    }
}