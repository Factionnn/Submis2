package com.fadhil.submissionpart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.fadhil.submissionpart2.databinding.ActivityUserDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserActivityDetail : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var viewModel: MainViewModel

    companion object{
        const val EXTRA_USERNAME= "extra_username"
        private val TAB_TITTLE = intArrayOf(
            R.string.follower,
            R.string.following


        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar?.title = "Detail User"

        val username = intent.getStringExtra(EXTRA_USERNAME)?:"null"
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,username)
        viewModel.setDetail(username)
        Log.d("Detail","Username : $username")
        viewModel.getUserDetail().observe(this,{
            binding.apply {
                Glide.with(this@UserActivityDetail)
                    .load(it.avatar_url)
                    .centerCrop()
                    .into(circleImageView)

                textname.text = it.name
                usernamed.text = it.login
                textcompany.text = it.company
                textlocation.text = it.location
                detailfollower.text = "${it.followers}"
                detailfollowing.text = "${it.following}"
                textrepository.text = "${it.bio}"

            }
        })

        val folAdapter = PageAdapter(this,bundle)
        val viewPager: ViewPager2 = findViewById(R.id.viewpager)
        viewPager.adapter = folAdapter
        val tabs: TabLayout = findViewById(R.id.table)
        TabLayoutMediator(tabs,viewPager){tab,position -> tab.text = resources.getString(TAB_TITTLE[position])}.attach()
        supportActionBar?.elevation = 0f


    }
}