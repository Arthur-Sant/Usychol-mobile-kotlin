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

class PatientReportViewModel : ViewModel() {

    private val reportDB = ReportDB()
    private val reportDAO = ReportImplementation(reportDB)
    private val reportRepository = ReportRepository(reportDAO)
    private val reportUseCases = ReportUseCase(reportRepository)

    private val activyDB = ActivyDB()
    private val activyDAO = ActivyImplementation(activyDB)
    private val activyRepository = ActivyRepository(activyDAO)
    private val activyUseCase = ActivyUseCase(activyRepository)

    private val _listActivy = MutableLiveData<List<Activy>>()
    val listActivy: LiveData<List<Activy>>
    get () = _listActivy

    fun getAllActivy(){
        val listActivys: List<Activy>? = activyUseCase.getAllActivy()

        if(listActivys != null){
            _listActivy.value = listActivys
        }
    }

}