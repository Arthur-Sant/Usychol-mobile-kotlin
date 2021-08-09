package com.project.usychol.implementations

import com.project.usychol.data.dao.ReportDAO
import com.project.usychol.db.ReportDB
import com.project.usychol.domain.entities.Report

class ReportImplementation(private val reportDB: ReportDB): ReportDAO {
    override fun create(report: Report) {
        reportDB.create(report)
    }

    override fun findAllByPatient(patientId: Int, psychologistId: Int): List<Report> {
        return reportDB.findAllByPatientId(patientId, psychologistId)
    }

    override fun findAllByPsychologist(psychologistId: Int): List<Report>?{
        return reportDB.findAllByPsychologistId(psychologistId)
    }
}