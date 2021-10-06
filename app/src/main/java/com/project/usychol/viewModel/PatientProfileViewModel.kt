package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.db.PatientDB
import com.project.usychol.domain.entities.Patient
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.useCases.PatientUseCase

class PatientProfileViewModel : ViewModel() {

    private val patientDAO = PatientImplementation()
    private val patientRepository = PatientRepository(patientDAO)
    private val patientUseCase = PatientUseCase(patientRepository)

    private var _patient = MutableLiveData<Patient>()
    val patient: LiveData<Patient>
    get() = _patient

    fun getPatientData(id: Int){
        val patientData: Patient? = patientUseCase.getPatientById(id)
        _patient.value = patientData!!
    }

    fun updatePatientData(patient: Patient){
        patientUseCase.updatePatientData(patient)
    }

}