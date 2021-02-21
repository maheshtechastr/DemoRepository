package com.mpg.shaadidemoapp.data.remote

import com.mpg.shaadidemoapp.data.entity.UserEntity

data class UserApiResponse(
    val results: List<UserEntity>,
    val info: Info
)

/*
 "seed": "27de8cd07171312e",
    "results": 10,
    "page": 1,
    "version": "1.3"
* */
data class Info(
    val seed: String,
    val page: Int
)