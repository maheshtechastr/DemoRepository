package com.mpg.shaadidemoapp.data.local

import androidx.lifecycle.LiveData
import com.mpg.shaadidemoapp.data.entity.UserEntity
import com.mpg.shaadidemoapp.data.entity.Result

interface LocalRepository {
    /**
     * To add User information into Database*/
    suspend fun addUser(userEntity: UserEntity)

    /**
     * Select a device by id.
     *
     */
    suspend fun getUserById(deviceId: String): UserEntity?
//    suspend fun getUserById(deviceId: String): Result<UserEntity>

    /**
     * To Fetch All Users from Database*/
    fun getUserList(): LiveData<List<UserEntity>>

    /**
     * To Fetch All Available Users from Database*/
//    fun observeAvailableUsers(): LiveData<List<UserEntity>>

    /**
     *  Get Result UserEntity List*/
    suspend fun getUsers(): Result<List<UserEntity>>

    /**
     * To update User information into Database*/
    suspend fun updateUser(userEntity: UserEntity): Int

    /**
     * To remove device record from Database*/
    suspend fun deleteUser(email: String): Int


}