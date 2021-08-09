package com.project.usychol.domain.entities

data class Report(
    var id: Int?,
    var activyTemplate: String,
    var resume: String,
    var consultationEvaluation: String,
    var reportDay: String,
    var psychologistId: Int,
    var patientId: Int
){
    init {
        if(id == null){
            id = (1..10000).random()
        }
    }
}
