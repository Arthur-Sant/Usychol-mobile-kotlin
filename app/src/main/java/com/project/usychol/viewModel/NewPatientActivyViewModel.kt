package com.project.usychol.viewModel

import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.ActivyRepository
import com.project.usychol.domain.entities.Activy
import com.project.usychol.implementations.ActivyImplementation
import com.project.usychol.useCases.ActivyUseCase

class NewPatientActivyViewModel : ViewModel(){

    private val activyDAO = ActivyImplementation()
    private val activyRepository = ActivyRepository(activyDAO)
    private val activyUseCase = ActivyUseCase(activyRepository)

//    fun createActivy(activy: Activy){
//        activyUseCase.createActivy(activy)
//    }

}