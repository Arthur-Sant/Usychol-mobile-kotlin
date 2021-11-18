package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.User
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.useCases.UserUseCase

class PLanViewModel : ViewModel(){
    private val psychologistDAO = UserImplementation()
    private val psychologistRepository = UserRepository(psychologistDAO)
    private val psychologistUseCases = UserUseCase(psychologistRepository)

    private var _task = MutableLiveData<Boolean>()
    val task: LiveData<Boolean>
        get () = _task

    fun choosePsychologistPlan(userId: String, plan: String){
        Thread {
            psychologistUseCases.choosePlan(userId, plan) {
                print(it)
                _task.postValue(it)
            }
        }.start()
    }
}