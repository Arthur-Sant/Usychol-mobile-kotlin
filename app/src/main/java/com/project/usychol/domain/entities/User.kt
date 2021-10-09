package com.project.usychol.domain.entities

data class User(
    var id: String?,
    var name: String,
    var crp: String,
    var cpf: String,
    var email: String,
    var password: String?,
    var age: String,
    var plan: String?
){
    init {
        if(id == null){
            id = (1..10000).random().toString()
        }
    }
}