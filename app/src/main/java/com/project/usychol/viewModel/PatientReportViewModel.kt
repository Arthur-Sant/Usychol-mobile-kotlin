package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.ActivyRepository
import com.project.usychol.data.repositories.PatientRepository
import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.Activy
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.ActivyImplementation
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.useCases.ActivyUseCase
import com.project.usychol.useCases.PatientUseCase
import com.project.usychol.useCases.ReportUseCase
import com.project.usychol.useCases.UserUseCase

class PatientReportViewModel : ViewModel() {

    private val reportDAO = ReportImplementation()
    private val reportRepository = ReportRepository(reportDAO)
    private val reportUseCase = ReportUseCase(reportRepository)

    private val patientDAO = PatientImplementation()
    private val patientRepository = PatientRepository(patientDAO)
    private val patientUseCase = PatientUseCase(patientRepository)

//    private val activyDAO = ActivyImplementation()
//    private val activyRepository = ActivyRepository(activyDAO)
//    private val activyUseCase = ActivyUseCase(activyRepository)

//    private val _listActivy = MutableLiveData<List<Activy>>()
//    val listActivy: LiveData<List<Activy>>
//    get () = _listActivy

    private val _reportData = MutableLiveData<Report>()
    val reportData: LiveData<Report>
    get () = _reportData

    private val _patientName = MutableLiveData<String>()
    val patientName: LiveData<String>
        get () = _patientName

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get () = _message

//    fun getAllActivy(){
//        val listActivys: List<Activy>? = activyUseCase.getAllActivy()
//
//        if(listActivys != null){
//            _listActivy.postValue(listActivys)
//        }
//    }

    fun getReportData(id: String){
        Thread {
            reportUseCase.getReportById(id) {
                if(it != null){
                    _reportData.postValue(it)
                }
            }
        }.start()
    }

    fun getPatientName(patientId: String){
        Thread{
            patientUseCase.getPatientById(patientId){
                if(it != null){
                    _patientName.postValue(it.name)
                }
            }
        }.start()
    }

    fun updateReportData(id: String, report: Report){
        Thread {
            reportUseCase.updateReport(id, report) {
                if(it != null){
                    _message.postValue(it)
                }else{
                    _message.postValue("Report updated successfully")
                }
            }
        }.start()
    }

}