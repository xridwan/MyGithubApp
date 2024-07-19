package id.eve.mygithubapp.presenter.following

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.eve.core.domain.usecase.UserUseCase

class FollowingViewModel(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    fun followings(loginId: String) = userUseCase.getFollowing(loginId).asLiveData()
}