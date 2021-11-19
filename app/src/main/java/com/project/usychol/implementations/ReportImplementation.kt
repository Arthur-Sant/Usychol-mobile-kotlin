package com.project.usychol.implementations

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.usychol.data.dao.ReportDAO
import com.project.usychol.domain.entities.Report

class ReportImplementation(): ReportDAO {
    private val database = Firebase.firestore
    private val collectionPath = "reports"

    override fun create(report: Report, returnId: (String?) -> Unit) {
        database.collection(collectionPath).document(report.id!!).set(report)
            .addOnSuccessListener {
                returnId(report.id!!)
            }
            .addOnFailureListener {
                returnId(null)
            }
    }

    override fun findById(id: String, returnReport: (Report?) -> Unit) {
        database.collection(collectionPath).document(id).get()
            .addOnSuccessListener {
                val user = it.toObject(Report::class.java)
                returnReport(user)
            }
            .addOnFailureListener {
                returnReport(null)
            }
    }

    override fun update(id: String, report: Report, returnError: (String?) -> Unit) {
        database.collection(collectionPath).document(id).set(report)
            .addOnSuccessListener {
                returnError(null)
            }
            .addOnFailureListener {
                returnError(it.localizedMessage)
            }
    }

    override fun delete(id: String, returnError: (String?) -> Unit) {
        database.collection(collectionPath).document(id).delete()
            .addOnSuccessListener {
                returnError(null)
            }
            .addOnFailureListener { error ->
                returnError(error.localizedMessage)
            }
    }

    override fun findAll( patientId: String, returnReports: (List<Report>?) -> Unit) {
        database.collection(collectionPath)
            .whereEqualTo("fromPatient", patientId)
            .get()
            .addOnSuccessListener {
                val reports = it.toObjects(Report::class.java).toList()
                returnReports(reports)
            }
            .addOnFailureListener {
                returnReports(null)
            }
    }
}