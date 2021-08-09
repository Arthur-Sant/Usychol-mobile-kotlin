package com.project.usychol.data.dao

import com.project.usychol.domain.entities.Activy

interface ActivyDAO {
    fun create(activy: Activy)

    fun findAll(): List<Activy>?
}