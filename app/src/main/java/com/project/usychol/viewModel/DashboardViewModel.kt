package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.data.repositories.ReminderRepository
import com.project.usychol.domain.entities.Patient
import com.project.usychol.domain.entities.Reminder
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.implementations.ReminderImplementation
import com.project.usychol.useCases.PatientUseCase
import com.project.usychol.useCases.ReminderUseCase

class DashboardViewModel : ViewModel() {
    private val patientDAO = PatientImplementation()
    private val patientRepository = PatientRepository(patientDAO)
    private val patientUseCases = PatientUseCase(patientRepository)

    private val reminderDAO = ReminderImplementation()
    private val reminderRepository = ReminderRepository(reminderDAO)
    private val reminderUseCases = ReminderUseCase(reminderRepository)

    private val _listUserReminder = MutableLiveData<List<Reminder>>()
    val listUserReminder: LiveData<List<Reminder>>
    get () = _listUserReminder

    private val _listPatient = MutableLiveData<List<Patient>>()
    val listPatient: LiveData<List<Patient>>
    get () = _listPatient

     fun getAllPatients(userId: String) {
        val patients = patientUseCases.getAllPatients(userId)
         if(patients != null){
             _listPatient.value = patients
         }
    }

    fun getAllUserReminder(){
        val reminders: List<Reminder>? = reminderUseCases.findAll()

        if(reminders != null){
            _listUserReminder.value = reminders
        }
    }

}