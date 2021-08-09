package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.db.ReportDB
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.useCases.ReportUseCase

class PatientInformationViewModel : ViewModel() {

    private val reportDB = ReportDB()
    private val reportDAO = ReportImplementation(reportDB)
    private val reportRepository = ReportRepository(reportDAO)
    private val reportUseCases = ReportUseCase(reportRepository)

    private val _listPatientReport = MutableLiveData<List<Report>>()
    val listPatientReport: LiveData<List<Report>>
        get () = _listPatientReport

    fun getAllPatientsReports(patientId: Int, psychologistId: Int) {
        val reports = reportUseCases.getAllByPatient(patientId, psychologistId)

        if(reports?.size != 0){

            println(reports)
            _listPatientReport.postValue(reports)
        }
    }

}