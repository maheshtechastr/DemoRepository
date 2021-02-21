package com.mpg.shaadidemoapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mpg.shaadidemoapp.data.entity.UserEntity


@Dao
interface InventoryDao {
    /**
     *
     * ***************************User Query**********************************
     *
     * */

    /**
     * Insert a User in the database. If the user already exists, replace it.
     *
     * @param userEntity the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(userEntity: UserEntity)

    /**
     * Observes list of Users.
     *
     * @return all Users.
     */
    @Query("SELECT * FROM UserEntity")
    fun observeUsers(): LiveData<List<UserEntity>>

    /**
     * Get list of Devices.
     *
     * @return all Devices.
     */
    @Query("SELECT * FROM UserEntity")
    fun getUsers(): List<UserEntity>



    /**
     * Delete a User by id.
     *
     * @return the number of users deleted. This should always be 1.
     */
    @Query("DELETE FROM UserEntity WHERE email = :email")
    suspend fun deleteUserById(email: String): Int

    /**
     * Observes a single User.
     *
     * @param email the email id.
     * @return the User with email.
     */
    @Query("SELECT * FROM UserEntity WHERE email = :email")
    fun observeUserById(email: String): LiveData<UserEntity>

    /**
     * Select a user by id.
     *
     * @param email the email id.
     * @return the User with email.
     */
    @Query("SELECT * FROM UserEntity WHERE email = :email")
    suspend fun getUserById(email: String): UserEntity?

    /**
     * Update a user.
     *
     * @param userEntity Device to be updated
     * @return the number of Users updated. This should always be 1.
     */
    @Update
    suspend fun updateUser(userEntity: UserEntity): Int


}