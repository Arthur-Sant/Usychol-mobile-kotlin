package com.project.usychol.data.dao

import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.Psychologist

interface PsychologistDAO {
    fun create(psychologist: Psychologist)

    fun findAll(): ArrayList<Psychologist>?

    fun findByEmail(email: String): Psychologist?

    fun update(id: Int, psychologist: Psychologist)

    fun updatePlan(userId: Int, plan: PLan)

    fun findById(id: Int): Psychologist?
}