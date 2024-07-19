package id.eve.mygithubapp.presenter.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.eve.core.domain.usecase.UserUseCase

class MainViewModel(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    fun getUsers(username: String) = userUseCase.getUsers(username).asLiveData()
}