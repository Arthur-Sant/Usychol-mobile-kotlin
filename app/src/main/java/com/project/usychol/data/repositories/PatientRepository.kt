package com.project.usychol.data.repositories

import com.project.usychol.data.dao.PatientDAO
import com.project.usychol.domain.entities.Patient

class PatientRepository(private val patientDAO: PatientDAO) {

    fun create(patient: Patient, returnId: (String?) -> Unit){
        patientDAO.create(patient){
                returnId(it)
        }
    }

    fun findAll(userId: String, returnPatients: (List<Patient>?) -> Unit) {
        patientDAO.findAll(userId){
            returnPatients(it)
        }
    }

    fun findById(id: String, returnPatient: (Patient?) -> Unit){
        patientDAO.findById(id){
            returnPatient(it)
        }
    }

    fun update(patient: Patient, returnError: (String?) -> Unit){
        val id = patient.id
        patientDAO.update(id!!, patient){
                returnError(it)
        }
    }

    fun delete(id: String, returnError: (String?) -> Unit){
        patientDAO.delete(id){
            returnError(it)
        }
    }

}