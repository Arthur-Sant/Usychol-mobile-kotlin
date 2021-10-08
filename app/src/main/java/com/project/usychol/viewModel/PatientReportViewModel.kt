package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.ActivyRepository
import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.domain.entities.Activy
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.ActivyImplementation
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.useCases.ActivyUseCase
import com.project.usychol.useCases.ReportUseCase

class PatientReportViewModel : ViewModel() {

    private val reportDAO = ReportImplementation()
    private val reportRepository = ReportRepository(reportDAO)
    private val reportUseCase = ReportUseCase(reportRepository)

    private val activyDAO = ActivyImplementation()
    private val activyRepository = ActivyRepository(activyDAO)
    private val activyUseCase = ActivyUseCase(activyRepository)

    private val _listActivy = MutableLiveData<List<Activy>>()
    val listActivy: LiveData<List<Activy>>
    get () = _listActivy

    private val _reportData = MutableLiveData<Report>()
    val reportData: LiveData<Report>
    get () = _reportData

    fun getAllActivy(){
//        val listActivys: List<Activy>? = activyUseCase.getAllActivy()
//
//        if(listActivys != null){
//            _listActivy.postValue(listActivys)
//        }
    }

    fun getReportData(userId: String, patientId: String, id: String){
        val report: Report? = reportUseCase.getReportById(userId, patientId, id)

        if(report != null){
            _reportData.postValue(report)
        }
    }

}