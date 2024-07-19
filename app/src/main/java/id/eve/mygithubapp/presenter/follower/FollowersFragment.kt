package id.eve.mygithubapp.presenter.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.eve.core.data.Resource
import id.eve.mygithubapp.databinding.FragmentFollowersBinding
import id.eve.mygithubapp.utils.hide
import id.eve.mygithubapp.utils.show
import id.eve.mygithubapp.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val followersViewModel: FollowersViewModel by viewModel()
    private lateinit var followersAdapter: FollowersAdapter

    companion object {
        var ARG_USERNAME = "username"

        fun newInstance(username: String?): FollowersFragment {
            val fragment = FollowersFragment()
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
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
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
        followersAdapter = FollowersAdapter()

        binding.apply {
            rvFollowers.layoutManager = LinearLayoutManager(requireContext())
            rvFollowers.setHasFixedSize(true)
            rvFollowers.adapter = followersAdapter
        }
    }

    private fun getData(loginId: String) {
        followersViewModel.followers(loginId).observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    val items = response.data
                    items?.let { followersAdapter.setData(it) }
                    binding.progressFollowers.hide()
                }

                is Resource.Loading -> {
                    binding.progressFollowers.show()
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