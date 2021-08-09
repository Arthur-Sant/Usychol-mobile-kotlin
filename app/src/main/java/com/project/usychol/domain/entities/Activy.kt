package com.project.usychol.domain.entities

data class Activy(
    var id: Int?,
    var deliveryDay: String,
    var description: String,
    var template: String
){
    init {
        if(id == null){
            id = (1..10000).random()
        }
    }
}
