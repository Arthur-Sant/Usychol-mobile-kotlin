package com.project.usychol.useCases

import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.domain.entities.Patient

class PatientUseCase(private val patientRepository: PatientRepository) {

    fun createPatient(userId: String, patient: Patient){
        patientRepository.create(userId, patient)
    }

    fun getAllPatients(userId: String): List<Patient>? {
        return patientRepository.findAll(userId)
    }

    fun getPatientById(userId: String, id: String): Patient?{
        return patientRepository.findById(userId, id)
    }

    fun updatePatientData(userId: String, patient: Patient){
        patientRepository.update(userId, patient)
    }

}