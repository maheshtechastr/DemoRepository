package com.mpg.shaadidemoapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/")
    suspend fun getUserList(@Query("results") results: Int): Response<UserApiResponse>

}