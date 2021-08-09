package com.project.usychol.viewModel

import androidx.lifecycle.ViewModel
import com.project.usychol.db.PsychologistDB
import com.project.usychol.data.repositories.PsychologistRepository
import com.project.usychol.domain.entities.Psychologist
import com.project.usychol.implementations.PsychologistImplementation
import com.project.usychol.useCases.PsychologistUseCase

class SignupViewModel: ViewModel() {

    private val psychologistDados = PsychologistDB()
    private val psychologistDAO = PsychologistImplementation(psychologistDados)
    private val psychologistRepository = PsychologistRepository(psychologistDAO)
    private val psychologistUseCases = PsychologistUseCase(psychologistRepository)

//    Uri.parse("file:///android_asset/images/jack_road.jpg"))
    // codigo guardado pra qaundo for pegar o caminho da imagem

    fun registerPsychologist(psychologist: Psychologist){
        psychologistUseCases.createUser(psychologist)
    }
}