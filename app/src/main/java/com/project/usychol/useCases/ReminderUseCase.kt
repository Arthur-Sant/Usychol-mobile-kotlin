package com.project.usychol.useCases

import com.project.usychol.data.repositories.ReminderRepository
import com.project.usychol.domain.entities.Reminder

class ReminderUseCase(private val reminderRepository: ReminderRepository) {
    fun create(reminder: Reminder){
        reminderRepository.create(reminder)
    }

    fun findAll(): ArrayList<Reminder>?{
        return reminderRepository.findAll()
    }

    fun update(reminder: Reminder){
        reminderRepository.update(reminder)
    }

    fun findById(id: String): Reminder?{
        return reminderRepository.findById(id)
    }

    fun delete(id: String){
        reminderRepository.delete(id)
    }
}