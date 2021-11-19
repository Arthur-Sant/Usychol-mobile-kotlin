package com.project.usychol.domain.entities

import java.util.*

class Patient (
    var id: String?,
    var name: String,
    var appointmentCount: Int,
    var patientClass: String,
    var motherName: String,
    var patientSummary: String,
    var fatherName: String,
    var maritalStatus: String,
    var age: String?,
    var fromUser: String,
    var profilePicture: String?
){
    constructor():this(
        null,
        "",
        0,
        "",
        "",
        "",
        "",
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
