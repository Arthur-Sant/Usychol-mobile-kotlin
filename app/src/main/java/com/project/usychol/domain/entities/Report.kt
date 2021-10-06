package com.project.usychol.domain.entities

data class Report(
    var id: String?,
    var activies: ArrayList<Activy>?,
    var resume: String,
    var startAt: String,
    var consultationEvaluation: Int,
    var date: String,
    var fromPatient: String
){
    init {
        if(id == null){
            id = (1..10000).random().toString()
        }
    }
}
