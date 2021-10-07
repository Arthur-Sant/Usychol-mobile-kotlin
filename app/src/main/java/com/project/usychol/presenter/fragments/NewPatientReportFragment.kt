package com.project.usychol.presenter.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentNewPatientReportBinding
import com.project.usychol.domain.entities.Report
import com.project.usychol.viewModel.NewPatientReportViewModel

class NewPatientReportFragment : Fragment() {

    private var _binding: FragmentNewPatientReportBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()

        val listActivyTemplate = resources.getStringArray(R.array.ddl_activy_name)
        val activyTemplateArrayAdapter = ArrayAdapter(requireContext(),
            R.layout.drop_down_text_options, listActivyTemplate)
        binding.ddlNewReportActivyName.setAdapter(activyTemplateArrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =  FragmentNewPatientReportBinding.inflate(inflater, container, false)

        val view: View = binding.root

        val newPatientReportViewModel = ViewModelProvider(this).get(NewPatientReportViewModel::class.java)

        val sharedPreferences = requireContext().getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val patientId = sharedPreferences.getString(getString(R.string.salved_patient_id_key), "")
        val userId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")

        binding.btnCreateReport.setOnClickListener {
            val report = createReport(patientId!!)

            if(report != null){
                newPatientReportViewModel.createReport(userId!!, patientId, report)
                backPatientInformationScreen(view)
            }else{
                Toast.makeText(activity, "fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNewReportBack.setOnClickListener {
            backPatientInformationScreen(view)
        }

        return view
    }

    private fun backPatientInformationScreen(view: View){
        Navigation.findNavController(view).navigate(R.id.newPatientReportToPatientInformation)
    }

    private fun createReport(patientId: String): Report?{

        val selectNewReportActivyName = binding.selectNewReportActivyName.editText!!.text
        val inputResumeNewReport = binding.inputResumeNewReport.text
        val inputConsultationNewReport = binding.inputConsultationNewReport.findViewById<EditText>(R.id.textInput).text
        val inputDayNewReport = binding.inputDayNewReport.findViewById<EditText>(R.id.textInput).text

        return if(inputResumeNewReport.isNotEmpty() && inputConsultationNewReport.isNotEmpty()
            && inputDayNewReport.isNotEmpty()){

            Report(
                null,
                null,
                inputResumeNewReport.toString(),
                inputDayNewReport.toString(),
                inputConsultationNewReport.toString().toInt(),
                inputDayNewReport.toString(),
                patientId
                )
        }else{
            null
        }
    }

}