package com.project.usychol.domain.entities

import com.google.firebase.firestore.DocumentId

class Reminder(
    @DocumentId
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
}

