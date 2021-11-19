package com.project.usychol.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.usychol.data.repositories.ReportRepository
import com.project.usychol.domain.entities.*
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.useCases.ReportUseCase

class NewPatientReportViewModel : ViewModel() {

    private val reportDAO = ReportImplementation()
    private val reportRepository = ReportRepository(reportDAO)
    private val reportUseCases = ReportUseCase(reportRepository)

//    private val activyDAO = ActivyImplementation()
//    private val activyRepository = ActivyRepository(activyDAO)
//    private val activyUseCase = ActivyUseCase(activyRepository)

    private var _reportId = MutableLiveData<String?>()
    val reportId: LiveData<String?>
        get () = _reportId


//    private var _listActivy = MutableLiveData<List<String>>()
//    val listActivy: LiveData<List<String>>
//        get () = _listActivy

//    fun getAllActivy(){
//        val listActivys: List<Activy>? = activyUseCase.getAllActivy()
//
//        if(listActivys != null) {
//            val listActivyTemplate = listActivys.map {
//                it.template
//            }
//
//            _listActivy.postValue(listActivyTemplate)
//        }
//    }

    fun createReport(report: Report){
        Thread {
            reportUseCases.createReport(report) { id ->
                _reportId.postValue(id)
            }
        }.start()
    }
}