package id.eve.mygithubapp.presenter.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.eve.core.data.Resource
import id.eve.mygithubapp.databinding.FragmentFollowingBinding
import id.eve.mygithubapp.utils.hide
import id.eve.mygithubapp.utils.show
import id.eve.mygithubapp.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val followingViewModel: FollowingViewModel by viewModel()
    private lateinit var followingAdapter: FollowingAdapter

    companion object {
        var ARG_USERNAME = "username"

        fun newInstance(username: String?): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val username = arguments?.getString(ARG_USERNAME)
            setAdapter()
            getData(username.toString())
        }
    }

    private fun setAdapter() {
        followingAdapter = FollowingAdapter()

        binding.apply {
            rvFollowing.layoutManager = LinearLayoutManager(requireContext())
            rvFollowing.setHasFixedSize(true)
            rvFollowing.adapter = followingAdapter
        }
    }

    private fun getData(loginId: String) {
        followingViewModel.followings(loginId).observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    val items = response.data
                    items?.let { followingAdapter.setData(it) }
                    binding.progressFollowing.hide()
                }

                is Resource.Loading -> {
                    binding.progressFollowing.show()
                }

                is Resource.Error -> {
                    toast(response.message.toString())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}