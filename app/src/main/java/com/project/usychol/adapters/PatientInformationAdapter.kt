package com.project.usychol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R
import com.project.usychol.adapters.viewHolder.PatientReportViewHolder
import com.project.usychol.domain.entities.Report

class PatientInformationAdapter(
    private val context: Context,
    private val listPatientReport: List<Report>,
    private val getPatientReportId: ((reportId: String) -> Unit)
) : RecyclerView.Adapter<PatientReportViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientReportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.patient_information_list_component,
            parent,
            false
        )

        return PatientReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientReportViewHolder, position: Int) {
        val patientReport = listPatientReport[position]

        holder.apply {
            tvPatientReportDate.text = patientReport.date

            itemView.setOnClickListener {
                getPatientReportId(patientReport.id!!)
            }
        }
    }

    override fun getItemCount(): Int = listPatientReport.size

}