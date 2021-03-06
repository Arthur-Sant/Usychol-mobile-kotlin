package com.project.usychol.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.usychol.R
import com.project.usychol.adapters.PatientInformationAdapter
import com.project.usychol.databinding.FragmentPatientInformationBinding
import com.project.usychol.domain.entities.Report
import com.project.usychol.viewModel.PatientInformationViewModel

class PatientInformationFragment : Fragment() {

    private lateinit var binding: FragmentPatientInformationBinding
    private lateinit var viewModel: PatientInformationViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPatientInformationBinding.inflate(inflater, container, false)

        val view: View = binding.root

        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val patientId = sharedPreferences.getString(getString(R.string.salved_patient_id_key), "")!!

        viewModel = ViewModelProvider(this).get(PatientInformationViewModel::class.java)

        viewModel.getAllPatientsReports(patientId)
        viewModel.getPatientName(patientId)


        startObservationPatient()
        startObservationPatientReports()

        binding.btnPatientInformationBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.patientInformationToDashboard)
        }

        binding.btnAccessPatientProfile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.patientInformationToPatientProfile)
        }

        binding.btnCreateActivityPatient.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.patientInformationToNewPatientActivity)
        }

        binding.btnCreatePatientReport.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.patientInformationToNewPatientReport)
        }

        binding.tvPatientSummary.setOnFocusChangeListener { v, hasFocus ->
            val summary = binding.tvPatientSummary.text.toString()
            if(!hasFocus){
                viewModel.updatePatientSummary(patientId, summary)
            }
        }

        return view
    }

    private fun startObservationPatient(){
        viewModel.patientData.observe(viewLifecycleOwner, Observer { patient ->
              binding.tvPatientName.text = patient[0]
              binding.tvPatientSummary.setText(patient[1])
        })
    }

    private fun startObservationPatientReports(){
        viewModel.listPatientReport.observe(viewLifecycleOwner, Observer{
            if(it != null){
                renderListPatientReport(it)
            }
        })
    }

    private fun renderListPatientReport(listPatientReport: List<Report>){
        val patientInformationAdapter = adapter(listPatientReport)

        binding.recyclerViewPatientReports.apply {
            adapter = patientInformationAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun adapter(listPatientReport: List<Report>): PatientInformationAdapter {

        val adapter = PatientInformationAdapter( requireContext(), listPatientReport,
            fun (reportId: String){
                sharedPreferences.edit {
                    putString(getString(R.string.salved_report_id_key), reportId)
                }

                Navigation.findNavController(binding.root).navigate(R.id.patientInformationToPatientReport)
            }
        )

        return adapter
    }

}