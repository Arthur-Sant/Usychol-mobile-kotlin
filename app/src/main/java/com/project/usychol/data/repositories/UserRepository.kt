package com.project.usychol.data.repositories

import com.project.usychol.data.dao.UserDAO
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.User

class UserRepository(private val userDAO: UserDAO) {
    fun create(user: User, returnError: (String?) -> Unit){
        userDAO.create(user){
            returnError(it)
        }
    }

    fun authenticateUser(email: String, password: String, returnUserStatus: (String) -> Unit){
        userDAO.authenticateUser(email, password){
            returnUserStatus(it)
        }
    }

    fun update(user: User, returnError: (String?) -> Unit){
        val id = user.id
        userDAO.update(id!!, user){
            returnError(it)
        }
    }

    fun updatePlan(userId: String, plan: String, performedTask: (Boolean) -> Unit){
        userDAO.updatePLan(userId, plan){
            performedTask(it)
        }
    }

    fun findById(id: String, returnUser: (User?) -> Unit){
        userDAO.findById(id){
            returnUser(it)
        }
    }

    fun delete(id: String, returnError: (String?) -> Unit){
        userDAO.delete(id){
            returnError(it)
        }
    }

}