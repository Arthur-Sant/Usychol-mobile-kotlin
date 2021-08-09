package com.project.usychol.data.dao

import com.project.usychol.domain.entities.Patient
import com.project.usychol.domain.entities.Psychologist

interface PatientDAO {
    fun create(patient: Patient)

    fun findAll(psychologistId: Int): List<Patient>?
//
//    fun findByEmail(email: String): Psychologist?
//
//    fun update(id: Int, psychologist: Psychologist)
//
//    fun updatePlan(userId: Int, plan: PLan)
//
//    fun findById(id: Int): Psychologist?
}