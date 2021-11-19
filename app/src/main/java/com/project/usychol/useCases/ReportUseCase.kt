package com.project.usychol.useCases

import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.domain.entities.Report

class ReportUseCase (private val reportRepository: ReportRepository){

    fun createReport(report: Report, returnId: (String?) -> Unit){
        reportRepository.create(report){
                returnId(it)
        }
    }

    fun getReportById(id: String, returnReport: (Report?) -> Unit){
        reportRepository.findById(id){
                returnReport(it)
        }
    }

    fun updateReport(id: String, report: Report, returnError: (String?) -> Unit){
        reportRepository.update(id, report){
                returnError(it)
        }
    }

    fun delete(id: String, returnError: (String?) -> Unit){
        reportRepository.delete(id){
            returnError(it)
        }
    }

    fun getAllReport(patientId: String, returnReports: (List<Report>?) -> Unit){
        reportRepository.findAll(patientId){
                returnReports(it)
        }
    }
}