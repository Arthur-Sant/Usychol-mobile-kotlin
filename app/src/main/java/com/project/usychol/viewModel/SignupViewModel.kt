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

    private var _messageError = MutableLiveData<String>()
    val messageError: LiveData<String>
        get () = _messageError

    fun registerUser(user: User){
        Thread {
            userUseCases.createUser(user) { error ->
                if(error != null){
                    _messageError.postValue(error)
                }else{
                    _messageError.postValue("Successfully registered user")
                }
            }
        }.start()
    }
}