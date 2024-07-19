package id.eve.mygithubapp.presenter.follower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.eve.core.domain.usecase.UserUseCase

class FollowersViewModel(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    fun followers(loginId: String) = userUseCase.getFollowers(loginId).asLiveData()
}