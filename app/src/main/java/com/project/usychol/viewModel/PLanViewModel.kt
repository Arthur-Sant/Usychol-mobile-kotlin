package com.project.usychol.viewModel

import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.PLan
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.useCases.UserUseCase

class PLanViewModel : ViewModel(){
    private val psychologistDAO = UserImplementation()
    private val psychologistRepository = UserRepository(psychologistDAO)
    private val psychologistUseCases = UserUseCase(psychologistRepository)

    fun choosePsychologistPlan(userId: String, plan: PLan){
        psychologistUseCases.choosePlan(userId, plan)
    }
}