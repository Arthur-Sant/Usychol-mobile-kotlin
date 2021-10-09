package com.project.usychol.presenter.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.usychol.R
import com.project.usychol.adapters.PatientInformationAdapter
import com.project.usychol.databinding.FragmentPatientInformationBinding
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.viewModel.PatientInformationViewModel

class PatientInformationFragment : Fragment() {

    private lateinit var binding: FragmentPatientInformationBinding
    private lateinit var patientInformationViewModel: PatientInformationViewModel
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
        val userId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")!!

//        patientInformationViewModel = ViewModelProvider(this).get(PatientInformationViewModel::class.java)

//        patientInformationViewModel.getAllPatientsReports(patientId!!, userId!!)
//        patientInformationViewModel.getPatientName(userId, patientId)


        startObservationPatient(userId, patientId)
        startObservationPatientReports(userId, patientId)

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

        binding.btnEditSummary.setOnClickListener {
            binding.tvPatientSummary.isEnabled = true
        }

        return view
    }

    private fun startObservationPatient(userId: String, patientId: String){
//        patientInformationViewModel.patientName.observe(viewLifecycleOwner, Observer { patient ->
          PatientImplementation().findById(userId, patientId) {
              binding.tvPatientName.text = it?.name
              binding.tvPatientSummary.text = it?.patientSummary
          }
//        })
    }

    private fun startObservationPatientReports(userId: String, patientId: String){
//        patientInformationViewModel.listPatientReport
//            .observe(viewLifecycleOwner, Observer { listPatientReports ->
        ReportImplementation().findAll(userId, patientId){
            if(it != null){
                renderListPatientReport(it)
            }
        }
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