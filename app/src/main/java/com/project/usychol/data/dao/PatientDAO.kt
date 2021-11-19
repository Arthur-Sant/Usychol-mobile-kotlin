package com.project.usychol.data.dao

import com.project.usychol.domain.entities.Patient

interface PatientDAO {
    fun create(patient: Patient, returnId: (String?) -> Unit)

    fun findAll( userId: String, returnPatients: (List<Patient>?) -> Unit)

    fun update( id: String, patient: Patient, returnError: (String?) -> Unit)

    fun findById(id: String, returnPatient: (Patient?) -> Unit)

    fun delete(id: String, returnError: (String?) -> Unit)

    fun updatePatientSummary(id: String, summary: String, performedTask: (Boolean) -> Unit)
}