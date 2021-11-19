package com.project.usychol.domain.entities

import com.google.firebase.firestore.DocumentId

class User(
    var id: String?,
    var name: String,
    var crp: String,
    var cpf: String,
    var email: String,
    var password: String?,
    var age: String,
    var plan: String?
){
    constructor():this(
        null,
        "",
        "",
        "",
        "",
        "",
        "",
        null
    ){}
}