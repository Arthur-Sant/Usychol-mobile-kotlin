package com.project.usychol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R
import com.project.usychol.adapters.viewHolder.PsychologistReminderViewHolder
import com.project.usychol.domain.entities.Report

class ReminderAdapter(
    private val context: Context,
    private val listPsychologistReminder: List<Report>
    ) : RecyclerView.Adapter<PsychologistReminderViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PsychologistReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.patients_reminder_list_component,
            parent,
            false
        )

        return PsychologistReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: PsychologistReminderViewHolder, position: Int) {
        val reminder = listPsychologistReminder[position]
        holder.apply {
            tvReportPatientName.text = "Arthur Santiago"
            tvReportPatientAge.text = reminder.patientId.toString()
            tvReportPatientTime.text = reminder.reportDay
        }
    }

    override fun getItemCount(): Int = listPsychologistReminder.size

}