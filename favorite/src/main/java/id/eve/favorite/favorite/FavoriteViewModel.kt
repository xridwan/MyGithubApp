package id.eve.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.eve.core.domain.usecase.UserUseCase

class FavoriteViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    fun getFavorite() = userUseCase.getFavorite().asLiveData()

    fun getData() = userUseCase.getData()

//    private var userDao: UserDao?
//    private var userDatabase: UserDatabase? = UserDatabase.getInstance(application)
//
//    init {
//        userDao = userDatabase?.favoriteDao()
//    }
//
//    fun getFavorite(): LiveData<List<Favorite>>? = userDao?.getFavorite()
}