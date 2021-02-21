package com.mpg.shaadidemoapp.data.remote

import retrofit2.Response

interface RemoteRepository {

    /**
     *  Get Result UserEntity List*/
    suspend fun getUsers(listCount: Int): Response<UserApiResponse>
}