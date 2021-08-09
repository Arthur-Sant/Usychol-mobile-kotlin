package com.project.usychol.implementations

import com.project.usychol.data.dao.ActivyDAO
import com.project.usychol.db.ActivyDB
import com.project.usychol.domain.entities.Activy

class ActivyImplementation(private val activyDB: ActivyDB) : ActivyDAO{
    override fun create(activy: Activy) {
        activyDB.create(activy)
    }

    override fun findAll(): List<Activy> {
        return activyDB.findAll()
    }
}