package com.mpg.shaadidemoapp.data.remote

import retrofit2.Response

class RemoteRepositoryImpl(private val userApi: UserApi) : RemoteRepository {

       override suspend fun getUsers(listCount: Int): Response <UserApiResponse> {
        return userApi.getUserList(listCount)

    }
}