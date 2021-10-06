package com.project.usychol.useCases

import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.domain.entities.Report

class ReportUseCase (private val reportRepository: ReportRepository){

    fun createReport(userId: String, patientId: String, report: Report){
        reportRepository.create(userId, patientId, report)
    }

    fun getReportById(userId: String, patientId: String, id: String): Report? {
        return reportRepository.findById(userId, patientId, id)
    }

    fun updateReport(userId: String, patientId: String, id: String, report: Report){
        reportRepository.update(userId, patientId, id, report)
    }

    fun delete(userId: String, patientId: String, id: String){
        reportRepository.delete(userId, patientId, id)
    }

    fun getAllReport(userId: String, patientId: String): ArrayList<Report>?{
        return reportRepository.findAll(userId, patientId)
    }
}