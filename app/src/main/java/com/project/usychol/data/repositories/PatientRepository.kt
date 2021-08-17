package com.project.usychol.data.repositories

import com.project.usychol.data.dao.PatientDAO
import com.project.usychol.domain.entities.Patient

class PatientRepository(private val patientDAO: PatientDAO) {

    fun create(patient: Patient){
        patientDAO.create(patient)
    }

    fun findAll(psychologistId: Int): List<Patient>? = patientDAO.findAll(psychologistId)

    fun findById(id: Int): Patient?{
        return patientDAO.findById(id)
    }

}