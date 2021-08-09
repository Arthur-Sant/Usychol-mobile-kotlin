package com.project.usychol.viewModel

import androidx.lifecycle.ViewModel
import com.project.usychol.db.PsychologistDB
import com.project.usychol.data.repositories.PsychologistRepository
import com.project.usychol.domain.entities.PLan
import com.project.usychol.implementations.PsychologistImplementation
import com.project.usychol.useCases.PsychologistUseCase

class PLanViewModel : ViewModel(){
    private val psychologistDados = PsychologistDB()
    private val psychologistDAO = PsychologistImplementation(psychologistDados)
    private val psychologistRepository = PsychologistRepository(psychologistDAO)
    private val psychologistUseCases = PsychologistUseCase(psychologistRepository)

    fun choosePsychologistPlan(userId: Int, plan: PLan){
        psychologistUseCases.choosePlan(userId, plan)
    }
}