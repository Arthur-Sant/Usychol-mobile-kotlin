package com.project.usychol.useCases

import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.User

class UserUseCase(private val userRepository: UserRepository) {
     fun createUser(user: User, returnError: (String?) -> Unit){
        userRepository.create(user){
            returnError(it)
        }
    }

    fun authenticateUser(email: String, password: String, returnUserStatus: (String) -> Unit){
        userRepository.authenticateUser(email, password){
            returnUserStatus(it)
        }
    }

    fun choosePlan(userId: String, pLan: String, performedTask: (Boolean) -> Unit){
        userRepository.updatePlan(userId, pLan){
            performedTask(it)
        }
    }

    fun findById(id: String, returnUser: (User?) -> Unit){
        userRepository.findById(id){
            returnUser(it)
        }
    }

    fun updateUser(user: User, returnError: (String?) -> Unit){
        userRepository.update(user){
            returnError(it)
        }
    }

    fun deleteUser(id: String, returnError: (String?) -> Unit){
        userRepository.delete(id){
            returnError(it)
        }
    }
}