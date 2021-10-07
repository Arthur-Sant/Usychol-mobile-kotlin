package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.User
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.useCases.UserUseCase

class SignupViewModel: ViewModel() {

    private val userDAO = UserImplementation()
    private val userRepository = UserRepository(userDAO)
    private val userUseCases = UserUseCase(userRepository)

    private var _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get () = _userId

    fun registerUser(user: User){
        userUseCases.createUser(user)
    }
}