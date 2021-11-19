package com.project.usychol.data.repositories

import com.project.usychol.data.dao.ReminderDAO
import com.project.usychol.domain.entities.Reminder

class ReminderRepository(private val reminderDAO: ReminderDAO) {
    fun create(reminder: Reminder, returnError: (String?) -> Unit){
        reminderDAO.create(reminder){
            returnError(it)
        }
    }

    fun findAll(userId: String, returnReminders: (List<Reminder>?) -> Unit){
        reminderDAO.findAll(userId) {
            returnReminders(it)
        }
    }

    fun update(reminder: Reminder){
//        val id = reminder.id
//        reminderDAO.update(id!!, reminder){}
    }

    fun findById(id: String) {
//        var reminder: Reminder? = null
//        reminderDAO.findById(id){
//            reminder = it
//        }
//
//        return reminder
    }

    fun delete(id: String){
//        reminderDAO.delete(id){}
    }

}