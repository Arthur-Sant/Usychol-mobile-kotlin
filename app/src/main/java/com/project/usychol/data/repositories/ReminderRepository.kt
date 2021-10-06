package com.project.usychol.data.repositories

import com.project.usychol.data.dao.ReminderDAO
import com.project.usychol.domain.entities.Reminder

class ReminderRepository(private val reminderDAO: ReminderDAO) {
    fun create(reminder: Reminder){
        reminderDAO.create(reminder){}
    }

    fun update(reminder: Reminder){
        val id = reminder.id
        reminderDAO.update(id!!, reminder){}
    }

    fun findById(id: String): Reminder? {
        var reminder: Reminder? = null
        reminderDAO.findById(id){
            reminder = it
        }

        return reminder
    }

    fun delete(id: String){
        reminderDAO.delete(id){}
    }

    fun findAll(): ArrayList<Reminder>?{
        var reminders: ArrayList<Reminder>? = null
        reminderDAO.findAll {
            reminders = it
        }

        return reminders
    }

}