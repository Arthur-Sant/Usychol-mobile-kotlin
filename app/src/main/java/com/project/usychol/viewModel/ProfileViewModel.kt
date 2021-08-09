package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.db.PsychologistDB
import com.project.usychol.data.repositories.PsychologistRepository
import com.project.usychol.domain.entities.Psychologist
import com.project.usychol.implementations.PsychologistImplementation
import com.project.usychol.useCases.PsychologistUseCase

class ProfileViewModel(private var id: Int) : ViewModel(){

    private val psychologistDados = PsychologistDB()
    private val psychologistDAO = PsychologistImplementation(psychologistDados)
    private val psychologistRepository = PsychologistRepository(psychologistDAO)
    private val psychologistUseCases = PsychologistUseCase(psychologistRepository)

    private var _user = MutableLiveData<Psychologist>()
    val user: LiveData<Psychologist>
    get () = _user

    init {
        getDataFromPsychologist()
    }

    private fun getDataFromPsychologist(){
        val psychologist = psychologistUseCases.findById(id)

        if(psychologist != null) {
            _user.postValue(psychologist)
        }
    }
}