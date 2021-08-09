package com.project.usychol.viewModel

import androidx.lifecycle.ViewModel
import com.project.usychol.db.PatientDB
import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.domain.entities.Patient
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.useCases.PatientUseCase

class RegisterPatientViewModel : ViewModel() {

    private val patientDados = PatientDB()
    private val patientDAO = PatientImplementation(patientDados)
    private val patientRepository = PatientRepository(patientDAO)
    private val patientUseCase = PatientUseCase(patientRepository)

    fun registerPatient(patient: Patient){
        patientUseCase.createPatient(patient)
    }

}