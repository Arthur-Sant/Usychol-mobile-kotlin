package com.project.usychol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R
import com.project.usychol.adapters.viewHolder.ActivyViewHolder
import com.project.usychol.domain.entities.Activy

class ActivyAdapter(private val context: Context)
    : RecyclerView.Adapter<ActivyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.patient_report_activities_list_component,
            parent,
            false
        )

        return ActivyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivyViewHolder, position: Int) {
//        val activy = listActivity[position]
        holder.tvReportActivyTemplate.text = "Thought of the day"
    }

    override fun getItemCount(): Int = 3

}