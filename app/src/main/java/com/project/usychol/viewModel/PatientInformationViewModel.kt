package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.useCases.PatientUseCase
import com.project.usychol.useCases.ReportUseCase

class PatientInformationViewModel : ViewModel() {

    private val patientDAO = PatientImplementation()
    private val patientRepository = PatientRepository(patientDAO)
    private val patientUseCase = PatientUseCase(patientRepository)

    private val reportDAO = ReportImplementation()
    private val reportRepository = ReportRepository(reportDAO)
    private val reportUseCases = ReportUseCase(reportRepository)

    private val _listPatientReport = MutableLiveData<List<Report>>()
    val listPatientReport: LiveData<List<Report>>
        get () = _listPatientReport

    private val _patientData = MutableLiveData<List<String>>()
    val patientData: LiveData<List<String>>
    get () = _patientData

//    fun getAllPatientsReports(patientId: String) {
//        val reports = reportUseCases.getAllReport(patientId)
//
//        if(reports?.size != 0){
//            _listPatientReport.postValue(reports)
//        }
//    }

    fun getPatientName(id: String){
         patientUseCase.getPatientById(id){ patient ->
            if(patient != null) {
                val patientInformations = ArrayList<String>()
                patientInformations.add(patient.name)
                patientInformations.add(patient.patientSummary)
                _patientData.postValue(patientInformations)
            }
        }
    }

}