package com.project.usychol.presenter.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.usychol.R
import com.project.usychol.adapters.ActivyAdapter
import com.project.usychol.databinding.FragmentPatientReportBinding
import com.project.usychol.domain.entities.Activy
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.implementations.ReportImplementation
import com.project.usychol.viewModel.PatientReportViewModel

class PatientReportFragment : Fragment() {

    private lateinit var binding: FragmentPatientReportBinding
    private lateinit var patientReportViewModel: PatientReportViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private var startAt: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPatientReportBinding.inflate(inflater, container, false)

        val view: View = binding.root

//        patientReportViewModel = ViewModelProvider(this).get(PatientReportViewModel::class.java)

        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val userId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")!!
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
                null,
                binding.tvPatientReportResume.text.toString(),
                startAt.toString(),
                binding.inputPatientReportConsultation.text.toString().toInt(),
                binding.tvPatientReportDate.text.toString(),
                patientId
            )

            ReportImplementation().update(userId, patientId, id, report){}
        }

        return view
    }

    private fun startReportObservation(){
        val id = sharedPreferences.getString(getString(R.string.salved_report_id_key), "")!!
        val userdId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")!!
        val patientId = sharedPreferences.getString(getString(R.string.salved_patient_id_key), "")!!

//        patientReportViewModel.getReportData(userdId, patientId, id)
        PatientImplementation().findById(userdId, patientId){
            binding.tvPatientReportName.text = it?.name
        }

        ReportImplementation().findById(userdId, patientId, id){
            binding.tvPatientReportResume.text = it?.resume
            binding.tvPatientReportDate.text = it?.date
            startAt = it?.startAt
            binding.inputPatientReportConsultation.text = it?.consultationEvaluation!!.toEditable()
        }

    }

    private fun Any.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this.toString())

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