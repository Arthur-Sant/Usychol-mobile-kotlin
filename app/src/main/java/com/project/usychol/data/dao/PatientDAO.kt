package com.project.usychol.data.dao

import com.project.usychol.domain.entities.Patient
import com.project.usychol.domain.entities.User

interface PatientDAO {
    fun create(
        userId: String,
        patient: Patient,
        res: (Patient?) -> Unit
    )

    fun findAll(
        userId: String,
        res: (ArrayList<Patient>?) -> Unit
    )

    fun update(
        userId: String,
        id: String,
        patient: Patient,
        res: (Patient?) -> Unit
    )

    fun findById(userId: String, id: String, res: (Patient?) -> Unit)

    fun delete(userId: String, id: String, res: (Patient?) -> Unit)
}