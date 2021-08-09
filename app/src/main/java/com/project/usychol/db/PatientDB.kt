package com.project.usychol.db

import com.project.usychol.domain.entities.Patient

class PatientDB {
    private var arrayPatientsList = ArrayList<Patient>()

    init {
            arrayPatientsList.add(
                Patient(
                    null,
                    null,
                    "Arthur Santigo",
                    "janeiro",
                    "teenager",
                    "Erci",
                    "João",
                    "Divorced",
                    17,
                    10
            )
            )

            arrayPatientsList.add(Patient(
                12,
                null,
                "Erick Vinicius",
                "janeiro",
                "child",
                "Luciana",
                "Paulo",
                "Divorced",
                4,
                10
            ))

        arrayPatientsList.add(Patient(
            14,
            null,
            "James Sirius",
            "janeiro",
            "child",
            "Luciana",
            "Paulo",
            "Divorced",
            4,
            10
        ))

        arrayPatientsList.add(Patient(
            13,
            null,
            "Davi Bernado",
            "janeiro",
            "child",
            "Luciana",
            "Paulo",
            "Divorced",
            4,
            10
        ))

        arrayPatientsList.add(Patient(
            12,
            null,
            "Erick Vinicius",
            "janeiro",
            "child",
            "Luciana",
            "Paulo",
            "Divorced",
            4,
            10
        ))
    }

    fun create(patient: Patient){
        arrayPatientsList.add(patient)
    }
    
    fun findAll(psychologistId: Int) = arrayPatientsList.filter {
        it.psychologistId == psychologistId
    }
}