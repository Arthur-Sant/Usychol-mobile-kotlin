package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.PatientRepository
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

    fun getPatientData( userId: String, id: String){
        val patientData: Patient? = patientUseCase.getPatientById(userId, id)
        _patient.postValue(patientData!!)
    }

    fun updatePatientData(userId: String, patient: Patient){
        patientUseCase.updatePatientData(userId ,patient)
    }

}