package com.fadhil.submissionpart2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.submissionpart2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar?.title = "Cari Pengguna"

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val intent = Intent (this@MainActivity, UserActivityDetail::class.java)
                with(intent) {
                    putExtra(UserActivityDetail.EXTRA_USERNAME,data.login)
                }
                startActivity(intent)
            }

        })
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            rvUser?.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser?.setHasFixedSize(true)
            rvUser?.adapter = adapter

            SearchView?.setOnClickListener {
                searching()
            }

            SearchView?.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    searching()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }
    private fun searching(){
        binding.apply {
            val searchText = SearchView
            if(searchText!!.isEmpty()) return
            showLoading(true)
            viewModel.setSearch(searchText.toString())
        }
    }
    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.loading!!.visibility = View.VISIBLE
        }else {
            binding.loading!!.visibility = View.GONE
        }
    }
}