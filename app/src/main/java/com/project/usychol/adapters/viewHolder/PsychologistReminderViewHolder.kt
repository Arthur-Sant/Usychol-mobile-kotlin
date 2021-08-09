package com.project.usychol.adapters.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R
import org.w3c.dom.Text

class PsychologistReminderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvReportPatientName = view.findViewById<TextView>(R.id.tvReportPatientName)
    val tvReportPatientAge = view.findViewById<TextView>(R.id.tvReportPatientAge)
    val tvReportPatientTime = view.findViewById<TextView>(R.id.tvReportPatientTime)
}