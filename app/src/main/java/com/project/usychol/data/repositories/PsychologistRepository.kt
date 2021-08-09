package com.project.usychol.data.repositories

import com.project.usychol.data.dao.PsychologistDAO
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.Psychologist

class PsychologistRepository(private val psychologistDAO: PsychologistDAO) {
    fun create(psychologist: Psychologist){
        psychologistDAO.create(psychologist)
    }

    fun findByEmail(email: String): Psychologist? = psychologistDAO.findByEmail(email)

    fun update(id: Int, psychologist: Psychologist){
        psychologistDAO.update(id, psychologist)
    }

    fun updatePlan(userId: Int, plan: PLan){
        psychologistDAO.updatePlan(userId, plan)
    }

    fun findById(id: Int): Psychologist? = psychologistDAO.findById(id)


}