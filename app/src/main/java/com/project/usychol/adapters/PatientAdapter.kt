package com.project.usychol.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R
import com.project.usychol.adapters.viewHolder.PatientViewHolder
import com.project.usychol.domain.entities.Patient
import com.project.usychol.shared.DataFormat
import java.lang.Exception

class PatientAdapter(
    private val context: Context,
    private val listPatients: List<Patient>,
    private val getPatientId: ((patientId: String) -> Unit)
    ) : RecyclerView.Adapter<PatientViewHolder>(){

    val data = DataFormat()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_list_component, parent,false)

        return PatientViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val listPatient = listPatients[position]
        val birthday = listPatient.age.toString().split(" ")

        try {
            val month = birthday[0]
            val day = birthday[1].replace(",", "")
            val year = birthday[2]
            val age = data.getDiffYear(month, day, year)

            holder.apply {
                tvPatientListName.text = listPatient.name
                tvPatientListAge.text = "Age: $age years"
                tvPatientListAppointments.text =
                    "${listPatient.appointmentCount} appointments - month"
                itemView.setOnClickListener {
                    getPatientId(listPatient.id!!)
                }
            }
        }catch (exc: Exception){}
    }

    override fun getItemCount(): Int = listPatients.size

    }