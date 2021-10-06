package com.project.usychol.useCases

import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.domain.entities.Patient

class PatientUseCase(private val patientRepository: PatientRepository) {

    fun createPatient(patient: Patient){
        patientRepository.create(patient)
    }

    fun getAllPatients(psychologistId: Int): List<Patient>? {
        return patientRepository.findAll(psychologistId)
    }

    fun getPatientById(id: Int): Patient?{
        return patientRepository.findById(id)
    }

    fun updatePatientData(patient: Patient){
        patientRepository.update(patient)
    }

}