package com.project.usychol.domain.entities

data class Psychologist(
    var id: Int?,
    var image: String?,
    var name: String,
    var birthday: String,
    var crpRegistration: Int,
    var cpf: String,
    var email: String,
    var password: String,
    var plan: PLan?
){
    init {
        if(id == null){
            id = (1..10000).random()
        }
    }
}