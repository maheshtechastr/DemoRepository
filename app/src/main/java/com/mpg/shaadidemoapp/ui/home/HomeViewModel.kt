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
    val userList: SingleLiveEvent<List<UserEntity>> = SingleLiveEvent()
    val showLoader = SingleLiveEvent<Boolean>()

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(userList) {
        it.isEmpty()
    }

    init {
        viewModelScope.launch {
            userList.postValue(repository.getUserList().value)//get Offline data
        }
    }

    fun fetchDataFromNetwork() = viewModelScope.launch {
        val results = repository.getUsers(10)
        when (results) {
            is Result.Success -> {
                showLoader.postValue(false)
                userList.postValue(results.data)
            }
            is Result.Error -> {
                showLoader.postValue(false)
                Timber.e(results.exception)
            }
            else -> {
                Timber.i(results.toString())
            }
        }
    }

    fun onButtonClicked(isApprove: Boolean) {

    }
}