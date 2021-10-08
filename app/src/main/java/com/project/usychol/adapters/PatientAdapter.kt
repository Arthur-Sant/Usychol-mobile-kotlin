package com.project.usychol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R
import com.project.usychol.adapters.viewHolder.PatientViewHolder
import com.project.usychol.domain.entities.Patient

class PatientAdapter(
    private val context: Context,
    private val listPatients: List<Patient>,
    private val getPatientId: ((patientId: String) -> Unit)
    ) : RecyclerView.Adapter<PatientViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_list_component, parent,false)

        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val listPatient = listPatients[position]
        holder.apply{
            tvPatientListName.text = listPatient.name
            tvPatientListAge.text = listPatient.age.toString()
            tvPatientListAppointments.text = "2x appointments - month"
            itemView.setOnClickListener {
                getPatientId(listPatient.id!!)
            }
        }
    }

    override fun getItemCount(): Int = listPatients.size

    }