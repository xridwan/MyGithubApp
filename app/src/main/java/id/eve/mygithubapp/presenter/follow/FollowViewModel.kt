package id.eve.mygithubapp.presenter.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.eve.core.domain.usecase.UserUseCase

class FollowViewModel(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    fun followers(loginId: String) = userUseCase.getFollowers(loginId).asLiveData()

    fun followings(loginId: String) = userUseCase.getFollowing(loginId).asLiveData()
}