package com.project.usychol.domain.entities

import java.util.*

class Reminder(
    var id: String?,
    var title: String = "",
    var startAt: String = "",
    var endAt: String = "",
    var fromUser: String?,
){
    constructor():this(
        null,
        "",
        "",
        "",
        null
    )

    init {
        if(id == null){
            id = UUID.randomUUID().toString()
        }
    }
}

