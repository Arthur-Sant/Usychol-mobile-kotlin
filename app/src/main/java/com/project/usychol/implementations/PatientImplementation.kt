package com.project.usychol.implementations

import com.project.usychol.db.PatientDB
import com.project.usychol.data.dao.PatientDAO
import com.project.usychol.domain.entities.Patient

class PatientImplementation (private val patientDados: PatientDB): PatientDAO {
    override fun create(patient: Patient) {
        patientDados.create(patient)
    }

    override fun findAll(psychologistId: Int): List<Patient> {
        return patientDados.findAll(psychologistId)
    }

}