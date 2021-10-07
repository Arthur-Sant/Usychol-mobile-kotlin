package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.useCases.UserUseCase

class SiginViewModel : ViewModel() {

    private val userDAO = UserImplementation()
    private val userRepository = UserRepository(userDAO)
    private val userUseCases = UserUseCase(userRepository)

    private var _loginSituation = MutableLiveData<String>()
    val loginSituation: LiveData<String>
        get () = _loginSituation

    private var _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get () = _userId

    fun loginUser(email: String, password: String){
        val user = userUseCases.findByEmail(email)

        if(user != null) {

            if (user.email == email && user.password == password) {

                _userId.value = user.id

                when(user.plan) {
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