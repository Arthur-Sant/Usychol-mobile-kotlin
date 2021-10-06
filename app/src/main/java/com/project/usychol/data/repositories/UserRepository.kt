package com.project.usychol.data.repositories

import com.project.usychol.data.dao.UserDAO
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.User

class UserRepository(private val userDAO: UserDAO) {
    fun create(user: User){
        userDAO.create(user){}
    }

    fun findByEmail(email: String): User?{
        return userDAO.findByEmail(email)
    }

    fun update(user: User){
        val id = user.id
        userDAO.update(id!!, user){}
    }

    fun updatePlan(userId: String, plan: PLan){
        userDAO.findById(userId){ user ->
            if(user != null) {
                user.plan = plan
                userDAO.updatePlan(user.id!!, user)
            }
        }
    }

    fun findById(id: String): User? {
        var user: User? = null
        userDAO.findById(id){
            user = it
        }

        return user
    }

    fun delete(id: String){
        userDAO.delete(id){}
    }

}