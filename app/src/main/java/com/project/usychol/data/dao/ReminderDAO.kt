package com.project.usychol.data.dao

import com.project.usychol.domain.entities.Reminder

interface ReminderDAO{
    fun create(reminder: Reminder, returnError: (String?) -> Unit)

    fun findAll(userId: String, returnReminders: (List<Reminder>?) -> Unit)

    fun update(id: String, reminder: Reminder, res: (Reminder?) -> Unit)

    fun findById(id: String, res: (Reminder?) -> Unit)

    fun delete(id: String, res: (Reminder?) -> Unit)
}