package com.mpg.shaadidemoapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.mpg.shaadidemoapp.base.BaseViewModel
import com.mpg.shaadidemoapp.common.SingleLiveEvent
import com.mpg.shaadidemoapp.data.Repository
import com.mpg.shaadidemoapp.data.entity.Result
import com.mpg.shaadidemoapp.data.entity.UserEntity
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    var userList: SingleLiveEvent<List<UserEntity>> = SingleLiveEvent()
    val showLoader = SingleLiveEvent<Boolean>()
    val showMessage = SingleLiveEvent<String>()
    val errorMsg = "OOPs something went wrong! try again later."

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(userList) {
        it.isEmpty()
    }

    init {
        fetchDataFromNetwork()
    }

    private fun fetchDataFromNetwork() = viewModelScope.launch {
        val results = repository.getUsers(10)
        try {
            when (results) {
                is Result.Success -> {
                    showLoader.postValue(false)

//                    val usersDb = repository.getUserList().value
                    var userListR = mutableListOf<UserEntity>()
                    for (item in results.data) {

//                        if(usr.value != null)
//                            userListR.add(getNewUserForStatus(usr.value., usr.value))
                        userListR.add(item)
                    }
                    userList.postValue(userListR)
//                    Timber.i("DB==>${abc.value}")
                }
                is Result.Error -> {
                    showLoader.postValue(false)
                    Timber.e(results.exception)
                }
                else -> {
                    Timber.i(results.toString())
                }
            }
        } catch (e: Exception) {
            showLoader.postValue(false)
            showMessage.postValue(errorMsg)
//            userList.postValue(listOf())
        }
    }

    fun onButtonClicked(isApprove: Boolean, user: UserEntity) {
        val msg: String
        val status: Int = if (isApprove) {
            msg = "Member Approved"
            1
        } else {
            msg = "Member Declined"
            0
        }
        try {

            Timber.i("User==>$user")

            viewModelScope.launch {
                var result = repository.updateUser(getNewUserForStatus(status, user))
                if (result > 0) {
                    showMessage.postValue(msg)

                } else showMessage.postValue(errorMsg)
                Timber.i("${repository.getUserById(user.email)}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getNewUserForStatus(status: Int, user: UserEntity): UserEntity = UserEntity(
        name = user.name,
        gender = user.gender,
        city = user.city,
        state = user.state,
        country = user.country,
        userStatus = status,
        dob = user.dob,
        picture = user.picture,
        email = user.email
    )
}