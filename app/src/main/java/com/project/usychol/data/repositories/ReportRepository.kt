package com.project.usychol.data.repositories

import com.project.usychol.data.dao.ReportDAO
import com.project.usychol.domain.entities.Report

class ReportRepository(private val reportDAO: ReportDAO){
    fun create(userId: String, patientId: String, report: Report){
        reportDAO.create(userId, patientId, report){}
    }

    fun findById(userId: String, patientId: String, id: String): Report? {
        var report: Report? = null
        reportDAO.findById(userId, patientId, id){
            report = it
        }

        return report
    }

    fun findAll(userId: String, patientId: String): ArrayList<Report>?{
        var reports: ArrayList<Report>? = null
        reportDAO.findAll(userId, patientId){
            reports = it
        }

        return reports
    }

    fun delete(userId: String, patientId: String, id: String){
        reportDAO.delete(userId, patientId, id){}
    }

    fun update(userId: String, patientId: String, id: String, report: Report){
        reportDAO.update(userId, patientId, id, report){}
    }
}