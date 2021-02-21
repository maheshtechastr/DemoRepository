package com.mpg.shaadidemoapp.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserEntity(
    @Embedded(prefix = "n_") val name: Name,
    val gender: String,
    val city: String?,
    val state: String?,
    val country: String?,
    val userStatus: Int = 0,
    @Embedded(prefix = "dob_") val dob: Dob,
    @Embedded(prefix = "pic_") val picture: Picture,
    @PrimaryKey val email: String
//    @PrimaryKey(autoGenerate = true) val deviceId: Int,
)

/*
"name": {
    "title": "Ms",
    "first": "Agnethe",
    "last": "Skjønberg"
},*/

data class Name(
    val title: String,
    val first: String,
    val last: String
)

data class Dob(
    val date: String,
    val age: Int
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)