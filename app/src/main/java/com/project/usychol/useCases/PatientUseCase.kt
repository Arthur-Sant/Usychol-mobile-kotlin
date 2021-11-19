package com.project.usychol.useCases

import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.domain.entities.Patient

class PatientUseCase(private val patientRepository: PatientRepository) {

    fun createPatient(patient: Patient, returnId: (String?) -> Unit){
        patientRepository.create(patient){
            returnId(it)
        }
    }

    fun getAllPatients(userId: String, returnPatients: (List<Patient>?) -> Unit){
        patientRepository.findAll(userId){
            returnPatients(it)
        }
    }

    fun updatePatientSummary(id: String, summary: String, performedTask: (Boolean) -> Unit){
        patientRepository.updatePatientSummary(id, summary){
            performedTask(it)
        }
    }

    fun getPatientById(id: String, returnPatient: (Patient?) -> Unit){
        patientRepository.findById(id){
            returnPatient(it)
        }
    }

    fun updatePatientData(patient: Patient, returnError: (String?) -> Unit){
        patientRepository.update(patient){
            returnError(it)
        }
    }

}