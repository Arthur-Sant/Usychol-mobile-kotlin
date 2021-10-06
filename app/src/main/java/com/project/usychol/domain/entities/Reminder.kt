package com.project.usychol.domain.entities

import com.google.gson.annotations.SerializedName

data class Reminder(
    var id: String?,
    var title: String = "",
    var startAt: String = "",
    var endAt: String = "",

    @SerializedName("profilePicture")
    var image: String?,

    var age: String?,
    var fromUser: String?,
){
    init {
        if(id == null){
            id = (1..10000).random().toString()
        }
    }
}

