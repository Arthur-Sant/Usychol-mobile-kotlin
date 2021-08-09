package com.project.usychol.data.repositories

import com.project.usychol.data.dao.ReportDAO
import com.project.usychol.domain.entities.Report

class ReportRepository(private val reportDAO: ReportDAO){
    fun create(report: Report){
        reportDAO.create(report)
    }

    fun findAllByPatientId(patientId: Int, psychologistId: Int): List<Report>?{
        return reportDAO.findAllByPatient(patientId, psychologistId)
    }

    fun findAllByPsychologist(psychologistId: Int): List<Report>?{
        return reportDAO.findAllByPsychologist(psychologistId)
    }
}