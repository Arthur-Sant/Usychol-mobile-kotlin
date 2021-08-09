package com.project.usychol.useCases

import com.project.usychol.data.repositories.ActivyRepository
import com.project.usychol.domain.entities.Activy

class ActivyUseCase(private val activyRepository: ActivyRepository) {

    fun createActivy(activy: Activy){
        activyRepository.create(activy)
    }

    fun getAllActivy(): List<Activy>?{
        return activyRepository.findAll()
    }
}