package com.project.usychol.db

import com.project.usychol.domain.entities.Patient
import com.project.usychol.domain.entities.Psychologist
import com.project.usychol.domain.entities.Report

class ReportDB {
    private var arrayReportList = ArrayList<Report>()

    init {
            arrayReportList.add(Report(
                100,
                "Thought of the day",
                "Parara pararar pararar pararar parara parara ayaaa aajajajaj",
                "Otimo",
                "04/12/2020 - 04:00 PM",
                10,
                12
            ))

            arrayReportList.add(Report(
                108,
                "Thought of the day",
                "Parara pararar pararar pararar parara parara ayaaa aajajajaj",
                "Otimo",
                "04/12/2020 - 04:00 PM",
                10,
                12
            ))

            arrayReportList.add(Report(
                100,
                "Thought of the day",
                "Parara pararar pararar pararar parara parara ayaaa aajajajaj",
                "Otimo",
                "04/12/2020 - 04:00 PM",
                10,
                12
            ))

            arrayReportList.add(Report(
                108,
                "Thought of the day",
                "Parara pararar pararar pararar parara parara ayaaa aajajajaj",
                "Otimo",
                "04/12/2020 - 04:00 PM",
                13,
                11
            ))
    }

    fun create(report: Report){
        arrayReportList.add(report)
    }

    fun findAllByPsychologistId(psychologistId: Int): List<Report>{
        return arrayReportList.filter {
            it.psychologistId == psychologistId
        }
    }

    fun findAllByPatientId(patientId: Int, psychologistId: Int): List<Report>{

        return arrayReportList.filter {
            it.psychologistId == psychologistId && it.patientId == patientId
        }

    }

    fun findById(id: Int): Report? {
        return arrayReportList.find {
            it.id == id
        }
    }


}