package com.project.usychol.data.dao

import com.project.usychol.domain.entities.Report

interface ReportDAO {
    fun create( report: Report, returnIdOrError: (String) -> Unit)

    fun findById(id: String, returnReport: (Report?) -> Unit)

    fun update( id: String, report: Report, returnError: (String?) -> Unit)

    fun delete( id: String, returnError: (String?) -> Unit )

    fun findAll( patientId: String, returnReports: (List<Report>?) -> Unit)

}