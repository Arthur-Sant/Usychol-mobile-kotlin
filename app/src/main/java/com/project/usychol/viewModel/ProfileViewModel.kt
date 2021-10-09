package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.User
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.useCases.UserUseCase

class ProfileViewModel() : ViewModel(){

    private val userDAO = UserImplementation()
    private val userRepository = UserRepository(userDAO)
    private val userUseCases = UserUseCase(userRepository)

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
    get () = _user

    fun getDataFromPsychologist(id: String){
        val user = userUseCases.findById(id)

        if(user != null) {
            _user.postValue(user!!)
        }
    }

    fun updateUserData(user: User){
        userUseCases.updateUser(user)
    }

    fun updateUserPlanData(userId: String, plan: String){
        userUseCases.choosePlan(userId, plan)
    }
}