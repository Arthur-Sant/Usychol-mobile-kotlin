package com.project.usychol.useCases

import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.domain.entities.Report

class ReportUseCase (private val reportRepository: ReportRepository){

    fun createReport(report: Report){
        reportRepository.create(report)
    }

    fun getReportById(id: Int): Report? {
        return reportRepository.findById(id)
    }

    fun getAllByPatient(patientId: Int, psychologistId: Int): List<Report>? {
        return reportRepository.findAllByPatientId(patientId, psychologistId)
    }

    fun getAllByPsychologist(psychologistId: Int): List<Report>?{
        return reportRepository.findAllByPsychologist(psychologistId)
    }
}