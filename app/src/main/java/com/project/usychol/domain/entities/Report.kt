package com.project.usychol.domain.entities

import com.google.firebase.firestore.DocumentId
import java.util.*
import kotlin.collections.ArrayList

class Report(
    var id: String?,
    var activies: List<String>?,
    var resume: String,
    var startAt: String,
    var consultEvaluation: Int,
    var date: String,
    var fromPatient: String
){
    constructor():this(
        null,
        null,
        "",
        "",
        0,
        "",
        ""
    )

    init {
        if(id == null){
            id = UUID.randomUUID().toString()
        }
    }
}
