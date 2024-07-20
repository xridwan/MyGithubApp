package id.eve.mygithubapp.presenter.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.eve.core.data.Resource
import id.eve.mygithubapp.databinding.FragmentFollowBinding
import id.eve.mygithubapp.utils.hide
import id.eve.mygithubapp.utils.show
import id.eve.mygithubapp.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowFragment : Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding
    private val followViewModel: FollowViewModel by viewModel()
    private lateinit var followersAdapter: FollowersAdapter
    private lateinit var followingAdapter: FollowingAdapter

    companion object {
        var ARG_USERNAME = "username"
        var ARG_TYPE = "type"

        fun newInstance(username: String?, type: Int): FollowFragment {
            val fragment = FollowFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            bundle.putInt(ARG_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val username = arguments?.getString(ARG_USERNAME)
            val type = arguments?.getInt(ARG_TYPE)
            if (type == 1) {
                setAdapter()
                username?.let { getFollower(it) }
            } else {
                setAdapterFollowing()
                username?.let { getFollowing(it) }
            }
        }
    }

    private fun setAdapter() {
        followersAdapter = FollowersAdapter()

        binding?.apply {
            rvFollowers.layoutManager = LinearLayoutManager(requireContext())
            rvFollowers.setHasFixedSize(true)
            rvFollowers.adapter = followersAdapter
        }
    }

    private fun setAdapterFollowing() {
        followingAdapter = FollowingAdapter()

        binding?.apply {
            rvFollowers.layoutManager = LinearLayoutManager(requireContext())
            rvFollowers.setHasFixedSize(true)
            rvFollowers.adapter = followingAdapter
        }
    }

    private fun getFollower(loginId: String) {
        followViewModel.followers(loginId).observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    val items = response.data
                    items?.let { followersAdapter.setData(it) }
                    binding?.progressFollowers?.hide()
                }
                is Resource.Loading -> {
                    binding?.progressFollowers?.show()
                }
                is Resource.Error -> {
                    toast(response.message.toString())
                }
            }
        }
    }

    private fun getFollowing(loginId: String) {
        followViewModel.followings(loginId).observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    val items = response.data
                    items?.let { followingAdapter.setData(it) }
                    binding?.progressFollowers?.hide()
                }
                is Resource.Loading -> {
                    binding?.progressFollowers?.show()
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