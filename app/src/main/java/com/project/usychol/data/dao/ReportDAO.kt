package com.project.usychol.data.dao

import com.project.usychol.domain.entities.Psychologist
import com.project.usychol.domain.entities.Report

interface ReportDAO {
    fun create(report: Report)

    fun findAllByPatient(patientId: Int, psychologistId: Int): List<Report>?

    fun findAllByPsychologist(psychologistId: Int): List<Report>?
}