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
    suspend fun getUserById(email: String): UserEntity?

    suspend fun getUserRById(email: String): Result<UserEntity>

    /**
     * To Fetch All Users from Database*/
    fun getUserList(): LiveData<List<UserEntity>>

    /**
     * To update User information into Database*/
    suspend fun updateUser(userEntity: UserEntity):Int

    /**
     * To remove device record from Database*/
    suspend fun deleteUser(email: String): Int

    /**
     * To Fetch All Available Users from Database*/
//    fun observeAvailableUsers(): LiveData<List<UserEntity>>

    /**
     *  Get Result UserEntity List*/
    suspend fun getUsers(itemCount: Int): Result<List<UserEntity>>

}