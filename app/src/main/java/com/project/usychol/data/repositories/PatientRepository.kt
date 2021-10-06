package com.project.usychol.data.repositories

import com.project.usychol.data.dao.PatientDAO
import com.project.usychol.domain.entities.Patient

class PatientRepository(private val patientDAO: PatientDAO) {

    fun create(userId: String, patient: Patient){
        patientDAO.create(userId, patient){}
    }

    fun findAll(userId: String): List<Patient>? {
        var patients: ArrayList<Patient>? = ArrayList()
        patientDAO.findAll(userId){
            patients = it
        }

        return patients
    }

    fun findById(userId: String, id: String): Patient?{
        var patient: Patient? = null
        patientDAO.findById(userId, id){
            patient = it
        }

        return patient
    }

    fun update(userId: String ,patient: Patient){
        val id = patient.id
        patientDAO.update(userId, id!!, patient){}
    }

    fun delete(userId: String, patientId: String){
        patientDAO.delete(userId, patientId){}
    }

}