package com.fadhil.submissionpart2

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
@GET("search/users")
@Headers("Authorization: token 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")
 fun getUser(
    @Query("q")query: String
 ):
        Call<UserResponse>


    @GET("users/{username}")
    @Headers("Authorization: token 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")
     fun getUserDetails(
        @Path("username") username: String
    ): Call <DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
     fun getUserfollowers(
        @Path("username") username: String
    ): Call <ArrayList<User>>

    @GET("search/{username}/following")
    @Headers("Authorization: token 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")
     fun getUserfollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

}