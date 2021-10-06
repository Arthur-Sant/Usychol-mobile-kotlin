package com.project.usychol.data.dao

import com.project.usychol.domain.entities.Report

interface ReportDAO {
    fun create(
        userId: String,
        patientId: String,
        report: Report,
        res: (Report?) -> Unit
    )

    fun findById(
        userId: String,
        patientId: String,
        id: String,
        res: (Report?) -> Unit
    )

    fun update(
        userId: String,
        patientId: String,
        id: String,
        report: Report,
        res: (Report?) -> Unit
    )

    fun delete(
        userId: String,
        patientId: String,
        id: String,
        res: (Report?) -> Unit
    )

    fun findAll(
        userId: String,
        patientId: String,
        res: (ArrayList<Report>?) -> Unit
    )

}