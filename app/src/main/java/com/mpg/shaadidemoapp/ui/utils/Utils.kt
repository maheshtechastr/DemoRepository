package com.mpg.shaadidemoapp.ui.utils

import com.mpg.shaadidemoapp.data.entity.UserStatus

object Utils {
    fun getUserStatusInt(userStatus: UserStatus):Int{
        return if (userStatus == UserStatus.NONE)
            0
        else if (userStatus == UserStatus.ACCEPTED)
            1
        else
            2
    }
}