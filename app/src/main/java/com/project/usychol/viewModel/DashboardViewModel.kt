package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.db.PatientDB
import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.db.ReportDB
import com.project.usychol.domain.entities.Patient
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.useCases.PatientUseCase
import com.project.usychol.useCases.ReportUseCase

class DashboardViewModel : ViewModel() {
    private val patientDB = PatientDB()
    private val patientDAO = PatientImplementation(patientDB)
    private val patientRepository = PatientRepository(patientDAO)
    private val patientUseCases = PatientUseCase(patientRepository)

    private val reportDB = ReportDB()
    private val reportDAO = ReportImplementation(reportDB)
    private val reportRepository = ReportRepository(reportDAO)
    private val reportUseCases = ReportUseCase(reportRepository)

    private val _listPsychologistReminder = MutableLiveData<List<Report>>()
    val listPsychologistReminder: LiveData<List<Report>>
    get () = _listPsychologistReminder

    private val _listPatient = MutableLiveData<List<Patient>>()
    val listPatient: LiveData<List<Patient>>
    get () = _listPatient

     fun getAllPatients(psychologistId: Int) {
        val patients = patientUseCases.getAllPatients(psychologistId)

         if(patients != null){
             _listPatient.postValue(patients)
         }
    }

    fun getAllPsychologistReminder(psychologistId: Int){
        val psychologistReminder: List<Report>? = reportUseCases.getAllByPsychologist(psychologistId)

        if(psychologistReminder != null){
            _listPsychologistReminder.value = psychologistReminder
        }
    }

}