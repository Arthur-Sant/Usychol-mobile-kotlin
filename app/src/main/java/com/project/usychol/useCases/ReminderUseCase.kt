package com.project.usychol.useCases

import com.project.usychol.data.repositories.ReminderRepository
import com.project.usychol.domain.entities.Reminder

class ReminderUseCase(private val reminderRepository: ReminderRepository) {
    fun create(reminder: Reminder, returnError: (String?) -> Unit){
        reminderRepository.create(reminder){
            returnError(it)
        }
    }

    fun findAll(userId: String, returnReminders: (List<Reminder>?) -> Unit){
        reminderRepository.findAll(userId){
            returnReminders(it)
        }
    }

    fun update(reminder: Reminder){
//        reminderRepository.update(reminder)
    }

    fun findById(id: String){
//        return reminderRepository.findById(id)
    }

    fun delete(id: String){
//        reminderRepository.delete(id)
    }
}