package com.project.usychol.useCases

import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.domain.entities.Patient

class PatientUseCase(private val patientRepository: PatientRepository) {

    fun createPatient(patient: Patient){
        patientRepository.create(patient)
    }

    fun getAllPatients(psychologistId: Int): List<Patient>? = patientRepository.findAll(psychologistId)

}