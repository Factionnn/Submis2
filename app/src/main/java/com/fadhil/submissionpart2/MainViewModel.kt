package com.fadhil.submissionpart2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

import javax.security.auth.callback.Callback

class MainViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()
    val UserDetail = MutableLiveData<DetailResponse>()


    fun setSearch(username: String){
        ApiConfig.getApiService()
            .getUser(username)
            .enqueue(object : retrofit2.Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun setDetail(username: String){
        ApiConfig.getApiService()
            .getUser(username)
            .enqueue(object : retrofit2.Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getUserDetail(): LiveData<DetailResponse> {
        return UserDetail
    }

    fun get(): LiveData<ArrayList<User>>{
        return listUsers
    }

    }



