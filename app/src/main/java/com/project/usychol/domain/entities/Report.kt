package com.project.usychol.domain.entities

import com.google.firebase.firestore.DocumentId

class Report(
    @DocumentId
    var id: String?,
    var activies: ArrayList<String>?,
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
}
