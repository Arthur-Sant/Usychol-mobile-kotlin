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

    private var _messageError = MutableLiveData<String>()
    val messageError: LiveData<String>
    get() = _messageError

    fun getDataFromPsychologist(id: String){
        Thread {
            userUseCases.findById(id) { user ->
                if (user != null) {
                    _user.postValue(user)
                }
            }
        }.start()
    }

    fun updateUserData(user: User){
        Thread {
            userUseCases.updateUser(user) { error ->
                if(error != null){
                    _messageError.postValue(error)
                }else{
                    _messageError.postValue("User updated successfully")
                }
            }
        }.start()
    }

    fun updateUserPlanData(userId: String, plan: String){
        Thread {
            userUseCases.choosePlan(userId, plan) {
                if(it) {
                    _messageError.postValue("Plan updated successfully")
                }
            }
        }.start()
    }

    fun deleteUser(userId: String){
        Thread{
            userUseCases.deleteUser(userId){
                if(it != null){
                    _messageError.postValue(it)
                }else{
                    _messageError.postValue("User deleted successfully")
                }
            }
        }.start()
    }
}