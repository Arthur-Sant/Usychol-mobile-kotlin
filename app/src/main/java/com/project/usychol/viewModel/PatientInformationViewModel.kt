package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.db.PatientDB
import com.project.usychol.db.ReportDB
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.useCases.PatientUseCase
import com.project.usychol.useCases.ReportUseCase

class PatientInformationViewModel : ViewModel() {

    private val patientDB = PatientDB()
    private val patientDAO = PatientImplementation(patientDB)
    private val patientRepository = PatientRepository(patientDAO)
    private val patientUseCase = PatientUseCase(patientRepository)

    private val reportDB = ReportDB()
    private val reportDAO = ReportImplementation(reportDB)
    private val reportRepository = ReportRepository(reportDAO)
    private val reportUseCases = ReportUseCase(reportRepository)

    private val _listPatientReport = MutableLiveData<List<Report>>()
    val listPatientReport: LiveData<List<Report>>
        get () = _listPatientReport

    private val _patientName = MutableLiveData<String>()
    val patientName: LiveData<String>
    get () = _patientName

    fun getAllPatientsReports(patientId: Int, psychologistId: Int) {
        val reports = reportUseCases.getAllByPatient(patientId, psychologistId)

        if(reports?.size != 0){

            println(reports)
            _listPatientReport.postValue(reports)
        }
    }

    fun getPatientName(id: Int){
        val (name) = patientUseCase.getPatientById(id)!!

        _patientName.value = name.toString()
    }

}