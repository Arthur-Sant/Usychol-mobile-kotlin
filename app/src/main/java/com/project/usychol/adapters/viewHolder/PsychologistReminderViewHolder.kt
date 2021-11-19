package com.project.usychol.adapters.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R
import org.w3c.dom.Text

class PsychologistReminderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvReminderAction = view.findViewById<TextView>(R.id.tvReminderAction)
    val tvReminderActionStart = view.findViewById<TextView>(R.id.tvReminderActionStart)
    val tvReminderActionEnd = view.findViewById<TextView>(R.id.tvReminderActionEnd)
}