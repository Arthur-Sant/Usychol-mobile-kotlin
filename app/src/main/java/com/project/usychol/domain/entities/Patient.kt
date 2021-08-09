package com.project.usychol.domain.entities

data class Patient (
    var id: Int?,
    var image: String?,
    var name: String,
    var birthday: String,
    var type: String,
    var momName: String,
    var fatherName: String,
    var maritalStatus: String,
    var age: Int?,
    var psychologistId: Int
){
    init {
        if(id == null){
            id = (1..10000).random()
        }
    }
}