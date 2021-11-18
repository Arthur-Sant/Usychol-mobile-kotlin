package com.project.usychol.data.dao

import com.project.usychol.domain.entities.User

interface UserDAO {
    fun create(body: User, returnError: (String?) -> Unit)

    fun authenticateUser(email: String, password: String, returnUserStatus: (String) -> Unit)

    fun update(id: String, body: User, returnError: (String?) -> Unit)

    fun findById(id: String, returnUser: (User?) -> Unit)

    fun delete(id: String, returnError: (String?) -> Unit)

    fun updatePLan(userId: String, plan: String, performedTask: (Boolean) -> Unit)
}