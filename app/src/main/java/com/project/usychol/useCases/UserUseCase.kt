package com.project.usychol.useCases

import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.User

class UserUseCase(private val userRepository: UserRepository) {
     fun createUser(user: User): String{
        return userRepository.create(user).toString()
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun choosePlan(userId: String, pLan: PLan){
        userRepository.updatePlan(userId, pLan)
    }

    fun findById(id: String): User? = userRepository.findById(id)

    fun updateUser(user: User){
        userRepository.update(user)
    }
}