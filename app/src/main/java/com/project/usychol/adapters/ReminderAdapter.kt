package com.project.usychol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R
import com.project.usychol.adapters.viewHolder.PsychologistReminderViewHolder
import com.project.usychol.domain.entities.Reminder
import com.project.usychol.domain.entities.Report

class ReminderAdapter(
    private val context: Context,
    private val listPsychologistReminder: List<Reminder>
    ) : RecyclerView.Adapter<PsychologistReminderViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PsychologistReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.action_reminder_list_component,
            parent,
            false
        )

        return PsychologistReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: PsychologistReminderViewHolder, position: Int) {
        val reminder = listPsychologistReminder[position]
        holder.apply {
            tvReminderAction.text = reminder.title
            tvReminderActionStart.text = reminder.startAt
            tvReminderActionEnd.text = reminder.endAt
        }
    }

    override fun getItemCount(): Int = listPsychologistReminder.size

}