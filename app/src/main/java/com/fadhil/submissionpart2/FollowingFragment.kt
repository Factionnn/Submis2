package com.fadhil.submissionpart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.submissionpart2.databinding.ActivityFollowerFragmentBinding
import com.fadhil.submissionpart2.databinding.ActivityFollowingFragmentBinding

class FollowingFragment : Fragment(R.layout.activity_following_fragment) {
    private var _binding : ActivityFollowingFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val args = arguments
        username = args?.getString(UserActivityDetail.EXTRA_USERNAME).toString()
        _binding = ActivityFollowingFragmentBinding.bind(view)
        adapter = UserAdapter()

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            listusers.layoutManager = LinearLayoutManager(activity)
            listusers.setHasFixedSize(true)
            listusers.adapter = adapter
        }

        showLoading (true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.setFollowing(username)
        viewModel.getFollowing().observe(viewLifecycleOwner,{
            if (it!=null){
                adapter.setList(it)
                showLoading(false)

            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.bars.visibility = View.VISIBLE
        } else{
            binding.bars.visibility = View.GONE
        }

    }
}