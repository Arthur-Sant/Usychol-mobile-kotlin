package com.project.usychol.data.repositories

import com.project.usychol.data.dao.ActivyDAO
import com.project.usychol.domain.entities.Activy

class ActivyRepository(private val activyDAO: ActivyDAO) {
    fun create(activy: Activy){
        activyDAO.create(activy)
    }

    fun findAll(){
    }
}