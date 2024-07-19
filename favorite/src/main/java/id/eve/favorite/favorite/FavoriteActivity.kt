package id.eve.favorite.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.eve.core.domain.model.UserData
import id.eve.favorite.databinding.ActivityFavoriteBinding
import id.eve.favorite.di.favoriteModule
import id.eve.mygithubapp.R
import id.eve.mygithubapp.presenter.detail.DetailActivity
import id.eve.mygithubapp.presenter.main.MainAdapter
import id.eve.mygithubapp.utils.hide
import id.eve.mygithubapp.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var mainAdapter: MainAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.label_favorite)
        }

        setAdapter()
        getfavorite()
    }

    override fun onResume() {
        super.onResume()
        getfavorite()
    }

    private fun setAdapter() {
        mainAdapter = MainAdapter()

        binding.apply {
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavorite.setHasFixedSize(true)
            rvFavorite.adapter = mainAdapter
        }

        mainAdapter.setOnItemCLickCallBack(object : MainAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: UserData) {
                selectedItem(data)
            }
        })
    }

    private fun selectedItem(data: UserData) {
        startActivity(
            Intent(
                this,
                DetailActivity::class.java
            ).putExtra(DetailActivity.EXTRA_DATA, data.login)
        )
    }

    private fun getfavorite() {
        favoriteViewModel.getFavorite().observe(this) {
            if (!it.isNullOrEmpty()) {
//                val list = setFavorite(it)
                mainAdapter.setData(it)
                binding.rvFavorite.show()
                binding.tvNotFound.hide()
            } else {
                binding.rvFavorite.hide()
                binding.tvNotFound.show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}