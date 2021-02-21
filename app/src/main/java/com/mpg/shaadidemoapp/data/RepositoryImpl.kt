package com.mpg.shaadidemoapp.data

import androidx.lifecycle.LiveData
import com.mpg.shaadidemoapp.data.entity.UserEntity

import com.mpg.shaadidemoapp.data.entity.Result
import com.mpg.shaadidemoapp.data.local.LocalRepository
import com.mpg.shaadidemoapp.data.remote.RemoteRepository
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) :
    Repository {
    override suspend fun addUser(userEntity: UserEntity) {
        localRepository.addUser(userEntity)
    }

    override suspend fun getUserById(email: String): UserEntity? {
        return localRepository.getUserById(email)
    }

    override suspend fun getUserRById(email: String): Result<UserEntity> {
        TODO("Not yet implemented")
    }

    override fun getUserList(): LiveData<List<UserEntity>> {
        return localRepository.getUserList()
    }

    override suspend fun updateUser(userEntity: UserEntity):Int {
        return localRepository.updateUser(userEntity)
    }

    override suspend fun deleteUser(email: String): Int {
        return localRepository.deleteUser(email)
    }

//    override fun observeAvailableUsers(): LiveData<List<UserEntity>> {
//        return localRepository.getUserList()
//    }


    override suspend fun getUsers(itemCount: Int): Result<List<UserEntity>> {
        return try {
            val response = remoteRepository.getUsers(itemCount)
            if (response.isSuccessful && response.body() != null) {
                val users = response.body()!!.results
                for (user in users) {
                    addUser(user)
                }
                Result.Success(users)
//                Result.Success(getUserList().value!!)
            } else Result.Error(IOException(response.message()))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }


}