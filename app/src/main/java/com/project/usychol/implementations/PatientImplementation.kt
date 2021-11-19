package com.project.usychol.implementations

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.usychol.data.dao.PatientDAO
import com.project.usychol.domain.entities.Patient

class PatientImplementation (): PatientDAO {
    private val database = Firebase.firestore
    private val collectionPath = "patients"

    override fun create(patient: Patient, returnId: (String?) -> Unit) {
        database.collection(collectionPath).document(patient.id!!).set(patient)
            .addOnSuccessListener {
                returnId(patient.id)
            }
            .addOnFailureListener {
                returnId(null)
            }
    }

    override fun findAll(userId: String, returnPatients: (List<Patient>?) -> Unit) {
        database.collection(collectionPath)
            .whereEqualTo("fromUser", userId)
            .get()
            .addOnSuccessListener {
                val patients = it.toObjects(Patient::class.java).toList()
                returnPatients(patients)
            }
            .addOnFailureListener {
                returnPatients(null)
            }
    }

    override fun update(id: String, patient: Patient, returnError: (String?) -> Unit) {
        database.collection(collectionPath).document(id).set(patient)
            .addOnSuccessListener {
                returnError(null)
            }
            .addOnFailureListener {
                returnError(it.localizedMessage)
            }
    }

    override fun findById(id: String, returnPatient: (Patient?) -> Unit) {
        database.collection(collectionPath).document(id).get()
            .addOnSuccessListener {
                val patient = it.toObject(Patient::class.java)
                returnPatient(patient)
            }
            .addOnFailureListener {
                returnPatient(null)
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

    override fun updatePatientSummary(id: String, summary: String, performedTask: (Boolean) -> Unit) {
        val reference = database.collection(collectionPath).document(id)
        reference.update(mapOf("patientSummary" to summary))
            .addOnSuccessListener{
                performedTask(true)
            }
            .addOnFailureListener {
                performedTask(false)
            }
    }
}