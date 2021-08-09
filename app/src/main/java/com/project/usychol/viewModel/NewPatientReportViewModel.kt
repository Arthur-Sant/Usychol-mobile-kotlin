package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.ActivyRepository
import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.db.ActivyDB
import com.project.usychol.db.ReportDB
import com.project.usychol.domain.entities.Activy
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.ActivyImplementation
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.useCases.ActivyUseCase
import com.project.usychol.useCases.ReportUseCase

class NewPatientReportViewModel : ViewModel() {

    private val reportDB = ReportDB()
    private val reportDAO = ReportImplementation(reportDB)
    private val reportRepository = ReportRepository(reportDAO)
    private val reportUseCases = ReportUseCase(reportRepository)

    private val activyDB = ActivyDB()
    private val activyDAO = ActivyImplementation(activyDB)
    private val activyRepository = ActivyRepository(activyDAO)
    private val activyUseCase = ActivyUseCase(activyRepository)

    private var _listActivy = MutableLiveData<List<String>>()
    val listActivy: LiveData<List<String>>
        get () = _listActivy

    fun getAllActivy(){
        val listActivys: List<Activy>? = activyUseCase.getAllActivy()

        if(listActivys != null) {
            val listActivyTemplate = listActivys.map {
                it.template
            }

            _listActivy.value = listActivyTemplate
        }
    }

    fun createReport(report: Report){
        reportUseCases.createReport(report)
    }
}