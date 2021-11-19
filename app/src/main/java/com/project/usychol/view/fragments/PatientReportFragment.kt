package com.project.usychol.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.usychol.R
import com.project.usychol.adapters.ActivyAdapter
import com.project.usychol.databinding.FragmentPatientReportBinding
import com.project.usychol.domain.entities.Report
import com.project.usychol.viewModel.PatientReportViewModel

class PatientReportFragment : Fragment() {

    private lateinit var binding: FragmentPatientReportBinding
    private lateinit var patientReportViewModel: PatientReportViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private var startAt: String? = null
    private var activies: List<String>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPatientReportBinding.inflate(inflater, container, false)

        val view: View = binding.root

        patientReportViewModel = ViewModelProvider(this).get(PatientReportViewModel::class.java)

        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val id = sharedPreferences.getString(getString(R.string.salved_report_id_key), "")!!
        val patientId = sharedPreferences.getString(getString(R.string.salved_patient_id_key), "")!!


        startReportObservation()
//        startActivyObservation()
        renderListActivy()

        binding.btnPatientProfileBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.patientReportToPatientInformation)
        }

        binding.btnEditPatientReport.setOnClickListener {
            val report = Report(
                id,
                activies,
                binding.tvPatientReportResume.text.toString(),
                startAt.toString(),
                binding.inputPatientReportConsultation.text.toString().toInt(),
                binding.tvPatientReportDate.text.toString(),
                patientId
            )

            patientReportViewModel.updateReportData(id, report)
        }

        patientReportViewModel.message.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            if(message.contains("successfully")){
                Navigation.findNavController(view).navigate(R.id.patientReportToPatientInformation)
            }
        })

        return view
    }

    private fun startReportObservation(){
        val id = sharedPreferences.getString(getString(R.string.salved_report_id_key), "")!!
        val patientId = sharedPreferences.getString(getString(R.string.salved_patient_id_key), "")!!

        patientReportViewModel.getReportData(id)
        patientReportViewModel.getPatientName(patientId)

        patientReportViewModel.patientName.observe(viewLifecycleOwner , Observer { name ->
            binding.tvPatientReportName.text = name
        })

        patientReportViewModel.reportData.observe(viewLifecycleOwner, Observer {
            binding.tvPatientReportResume.text = it.resume
            binding.tvPatientReportDate.text = it.date
            startAt = it.startAt
            activies = it.activies
            binding.inputPatientReportConsultation.setText(it.consultEvaluation.toString())
        })

    }

//    private fun startActivyObservation(){
//        patientReportViewModel.getAllActivy()
//
//        patientReportViewModel.listActivy.observe(viewLifecycleOwner, Observer { listActivy ->
//            if(listActivy != null){
//                renderListActivy(listActivy)
//            }
//        })
//    }

    private fun renderListActivy(){
        val activyAdapter = ActivyAdapter(requireContext())
        binding.recyclerViewReportActivy.apply {
            adapter = activyAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

}