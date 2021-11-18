package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.domain.entities.Patient
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.useCases.PatientUseCase

class RegisterPatientViewModel : ViewModel() {

    private val patientDAO = PatientImplementation()
    private val patientRepository = PatientRepository(patientDAO)
    private val patientUseCase = PatientUseCase(patientRepository)

    private var _patientId = MutableLiveData<String?>()
    val patientId: LiveData<String?>
        get () = _patientId

    fun registerPatient(patient: Patient){
        Thread{
            patientUseCase.createPatient(patient){ id ->
                _patientId.postValue(id)
            }
        }.start()
    }
}