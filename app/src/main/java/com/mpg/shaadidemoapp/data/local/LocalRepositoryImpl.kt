package com.mpg.shaadidemoapp.data.local

import androidx.lifecycle.LiveData
import com.mpg.shaadidemoapp.data.entity.UserEntity
import com.mpg.shaadidemoapp.data.entity.Result
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: InventoryDao,
//    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalRepository {
    override suspend fun addUser(userEntity: UserEntity) {
        dao.addUser(userEntity)
    }

    override suspend fun getUserById(email: String): UserEntity? {
        return dao.getUserById(email)
    }

    override fun getUserList(): LiveData<List<UserEntity>> {
        return dao.observeUsers()
    }

//    override fun observeAvailableUsers(): LiveData<List<UserEntity>> {
//        return dao.observeUsers()
//    }

    override suspend fun getUsers(): Result<List<UserEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userEntity: UserEntity): Int {
        return dao.updateUser(userEntity)
    }

    override suspend fun deleteUser(email: String): Int {
        TODO("Not yet implemented")
    }
//    /**
//     * To add Device information into Database*/
//    override suspend fun addDevice(userEntity: UserEntity) {
//        dao.addDevice(userEntity)
//    }
//
//
//    override suspend fun getDeviceRById(deviceId: Int): Result<UserEntity> {
//        val deviceEntity = getDeviceById(deviceId)
//        return if (deviceEntity != null)
//            Result.Success(deviceEntity)
//        else Result.Error(Exception("Device Not Found!"))
//    }
//
//    /**
//     * To Fetch All Devices from Database*/
//    override fun getDeviceList(): LiveData<List<UserEntity>> {
//        return dao.observeDevices()
//    }


}