package com.project.usychol.data.repositories

import com.project.usychol.data.dao.ReportDAO
import com.project.usychol.domain.entities.Report

class ReportRepository(private val reportDAO: ReportDAO){
    fun create(report: Report, returnId: (String?) -> Unit){
        reportDAO.create(report){
            returnId(it)
        }
    }

    fun findById(id: String, returnReport: (Report?) -> Unit){
        reportDAO.findById(id){
            returnReport(it)
        }
    }

    fun findAll(patientId: String, returnReports: (List<Report>?) -> Unit){
        reportDAO.findAll(patientId){
            returnReports(it)
        }
    }

    fun delete(id: String, returnError: (String?) -> Unit){
        reportDAO.delete(id){
            returnError(it)
        }
    }

    fun update(id: String, report: Report, returnError: (String?) -> Unit){
        reportDAO.update(id, report){
            returnError(it)
        }
    }
}