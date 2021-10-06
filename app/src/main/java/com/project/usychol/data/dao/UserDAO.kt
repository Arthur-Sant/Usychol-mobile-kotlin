package com.project.usychol.data.dao

import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.User

interface UserDAO {
    fun create(body: User, res: (User?) -> Unit)

    fun findAll(res: (ArrayList<User>?) -> Unit)

    fun findByEmail(email: String): User?

    fun update(id: String, body: User, res: (User?) -> Unit)

    fun updatePlan(userId: String, body: User)

    fun findById(id: String, res: (User?) -> Unit)

    fun delete(id: String, res: (User?) -> Unit)
}