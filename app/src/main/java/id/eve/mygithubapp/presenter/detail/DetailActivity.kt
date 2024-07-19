package id.eve.mygithubapp.presenter.detail

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import id.eve.core.data.Resource
import id.eve.core.domain.model.DetailData
import id.eve.mygithubapp.R
import id.eve.mygithubapp.databinding.ActivityDetailBinding
import id.eve.mygithubapp.utils.hide
import id.eve.mygithubapp.utils.show
import id.eve.mygithubapp.utils.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    private val detail = DetailActivity::class.simpleName
    private var detailData: DetailData? = null
    private var isChecked = false

    companion object {
        const val EXTRA_DATA = "extra_data"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tabs_followers,
            R.string.tabs_following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.label_detail)
            elevation = 0.0f
        }

//        val data = intent.getParcelableExtra<UserResponse>(EXTRA_DATA) as UserResponse
        val login = intent.getStringExtra(EXTRA_DATA)
//        val id = data.id
        val mLogin = login
//        val avatar = data.avatarUrl

        CoroutineScope(Dispatchers.IO).launch {
            val count = detailViewModel.checkFavorite(login ?: "")
            withContext(Dispatchers.Main) {
                if (count > 0) {
                    binding.tbFavorite.isChecked = true
                    isChecked = true
                } else {
                    binding.tbFavorite.isChecked = false
                    isChecked = false
                }
            }
        }

        binding.tbFavorite.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                detailViewModel.addFavorite(DetailData.mapToFavorite(detailData))
                toast("Success added")
            } else {
                detailViewModel.removeFavorite(detailData?.id ?: 0)
                toast("Success removed")
            }
            binding.tbFavorite.isChecked = isChecked
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = login
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        login?.let { getData(it) }
    }

    private fun getData(username: String) {
        detailViewModel.getUser(username).observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    detailData = response.data
                    setContent()
                    binding.progressDetail.hide()
                }

                is Resource.Loading -> {
                    binding.progressDetail.show()
                }

                is Resource.Error -> {
                    toast(response.message.toString())
                    binding.progressDetail.hide()
                }
            }
        }
    }

    private fun setContent() {
        binding.apply {
            Picasso.get().load(detailData?.avatarUrl).into(imgAvatarDetail)
            detailName.text = detailData?.name
            detailLogin.text = detailData?.login
            detailCompany.text = "-"
            detailCompany.text = detailData?.company
            detailLocation.text = detailData?.location
            detailRepository.text =
                getString(R.string.label_repository, detailData?.repository)
            detailFollowers.text =
                getString(R.string.label_followers, detailData?.followers.toString())
            detailFollowing.text =
                getString(R.string.label_following, detailData?.following.toString())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}