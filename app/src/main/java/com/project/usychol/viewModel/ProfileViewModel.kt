package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.User
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.useCases.UserUseCase

class ProfileViewModel(private var id: String) : ViewModel(){

    private val psychologistDAO = UserImplementation()
    private val psychologistRepository = UserRepository(psychologistDAO)
    private val psychologistUseCases = UserUseCase(psychologistRepository)

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
    get () = _user

    init {
        getDataFromPsychologist()
    }

    private fun getDataFromPsychologist(){
        val psychologist = psychologistUseCases.findById("2")
        println(psychologist)

        if(psychologist != null) {
            _user.postValue(psychologist)
        }
    }

    fun updateUserData(user: User){
        psychologistUseCases.updateUser(user)
    }

    fun updateUserPlanData(userId: String, plan: PLan){
        psychologistUseCases.choosePlan(userId, plan)
    }
}