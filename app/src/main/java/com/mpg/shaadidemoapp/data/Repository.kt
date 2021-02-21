package com.mpg.shaadidemoapp.data

import androidx.lifecycle.LiveData

import com.mpg.shaadidemoapp.data.entity.UserEntity
import com.mpg.shaadidemoapp.data.entity.Result

interface Repository {
    /**
     * To add User information into Database*/
    suspend fun addUser(userEntity: UserEntity)

    /**
     * Select a device by id.
     *
     */
    suspend fun getUserById(deviceId: Int): UserEntity?

    suspend fun getUserRById(deviceId: Int): Result<UserEntity>
    /**
     * To Fetch All Users from Database*/
    fun getUserList(): LiveData<List<UserEntity>>

    /**
     * To update User information into Database*/
    suspend fun updateUser(userEntity: UserEntity)

    /**
     * To remove device record from Database*/
    suspend fun deleteUser(deviceId: Int): Int

    /**
     * To Fetch All Available Users from Database*/
    fun observeAvailableUsers(): LiveData<List<UserEntity>>

    /**
     *  Get Result UserEntity List*/
    suspend fun getUsers(itemCount:Int): Result<List<UserEntity>>

}