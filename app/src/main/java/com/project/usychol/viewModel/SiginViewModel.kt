package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.db.PsychologistDB
import com.project.usychol.data.repositories.PsychologistRepository
import com.project.usychol.implementations.PsychologistImplementation
import com.project.usychol.useCases.PsychologistUseCase

class SiginViewModel : ViewModel() {

    private val psychologistDados = PsychologistDB()
    private val psychologistDAO = PsychologistImplementation(psychologistDados)
    private val psychologistRepository = PsychologistRepository(psychologistDAO)
    private val psychologistUseCases = PsychologistUseCase(psychologistRepository)

    private var _loginSituation = MutableLiveData<String>()
    val loginSituation: LiveData<String>
        get () = _loginSituation

    private var _userId = MutableLiveData<Int>()
    val userId: LiveData<Int>
        get () = _userId

    fun loginUser(email: String, password: String){
        val psychologist = psychologistUseCases.findByEmail(email)

        if(psychologist != null) {

            if (psychologist.email == email && psychologist.password == password) {

                _userId.value = psychologist.id

                when(psychologist.plan) {
                    null -> _loginSituation.value = "logged in but no plan"
                    else -> _loginSituation.value = "logged"
                }

            }else{
                _loginSituation.value = "not logged"
            }
        }else{
            _loginSituation.value = "not logged"
        }
    }
}