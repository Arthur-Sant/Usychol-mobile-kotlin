package com.project.usychol.adapters.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R

class PatientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tvPatientListName = view.findViewById<TextView>(R.id.tvPatientListName)
    var tvPatientListAge = view.findViewById<TextView>(R.id.tvPatientListAge)
    var tvPatientListAppointments = view.findViewById<TextView>(R.id.tvPatientListAppointments)
}