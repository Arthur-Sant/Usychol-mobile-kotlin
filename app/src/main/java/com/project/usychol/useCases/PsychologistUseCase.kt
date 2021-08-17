package com.project.usychol.useCases

import com.project.usychol.data.repositories.PsychologistRepository
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.Psychologist

class PsychologistUseCase(private val psychologistRepository: PsychologistRepository) {
     fun createUser(psychologist: Psychologist){
        psychologistRepository.create(psychologist)
    }

    fun findByEmail(email: String): Psychologist? {
        return psychologistRepository.findByEmail(email)
    }

    fun choosePlan(userId: Int, plan: PLan){
        psychologistRepository.updatePlan(userId, plan)
    }

    fun findById(id: Int): Psychologist? = psychologistRepository.findById(id)

    fun updateUser(id: Int, psychologist: Psychologist){
        psychologistRepository.update(id, psychologist)
    }
}