package id.eve.core.domain.usecase

import android.database.Cursor
import id.eve.core.data.local.entity.Favorite
import id.eve.core.data.Resource
import id.eve.core.domain.model.DetailData
import id.eve.core.domain.model.FollowerData
import id.eve.core.domain.model.FollowingData
import id.eve.core.domain.model.UserData
import id.eve.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(
    private val userRepository: UserRepository,
) : UserUseCase {
    override fun getUsers(username: String): Flow<Resource<List<UserData>>> {
        return userRepository.getUsers(username)
    }

    override fun getFavorite(): Flow<List<UserData>> {
        return userRepository.getFavorite()
    }

    override fun getUser(username: String): Flow<Resource<DetailData>> {
        return userRepository.getUser(username)
    }

    override fun getFollowers(username: String): Flow<Resource<List<FollowerData>>> {
        return userRepository.getFollowers(username)
    }

    override fun getFollowing(username: String): Flow<Resource<List<FollowingData>>> {
        return userRepository.getFollowing(username)
    }

    override suspend fun addFavorite(favorite: Favorite) {
        return userRepository.addFavorite(favorite)
    }

    override suspend fun removeFavorite(id: Int) {
        return userRepository.removeFavorite(id)
    }

    override suspend fun checkFavorite(id: String): Int {
        return userRepository.checkFavorite(id)
    }

    override fun getData(): Cursor {
        return userRepository.getData()
    }
}