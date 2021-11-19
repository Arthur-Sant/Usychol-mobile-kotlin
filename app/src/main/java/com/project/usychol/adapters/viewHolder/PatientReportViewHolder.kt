package com.project.usychol.adapters.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R

class PatientReportViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var tvPatientReportDate = view.findViewById<TextView>(R.id.tvPatientInformationDate)
    var tvPatientReportTime = view.findViewById<TextView>(R.id.tvPatientInformationTime)
}