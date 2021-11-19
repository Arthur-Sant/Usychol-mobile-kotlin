package com.project.usychol.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentNewPatientReportBinding
import com.project.usychol.domain.entities.Report
import com.project.usychol.shared.DataFormat
import com.project.usychol.viewModel.NewPatientReportViewModel

class NewPatientReportFragment : Fragment() {

    private var _binding: FragmentNewPatientReportBinding? = null
    private val binding get() = _binding!!

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

        val patientId = sharedPreferences.getString(getString(R.string.salved_patient_id_key), "")!!

        binding.btnCreateReport.setOnClickListener {
            val report = createReport(patientId)

            if(report?.date == "") {
                Toast.makeText(
                    requireContext(),
                    "Insira a data do report do modo que foi proposto, mes em " +
                            "ingles, dia, ano e hora",
                    Toast.LENGTH_LONG
                ).show()

            }else if(report != null){
                newPatientReportViewModel.createReport(report)
            }else{
                Toast.makeText(activity, "fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNewReportBack.setOnClickListener {
            backPatientInformationScreen(view)
        }

        newPatientReportViewModel.reportId.observe(viewLifecycleOwner, Observer { id ->
            if(id != null){
                Toast.makeText(requireContext(), "Report created successfully", Toast.LENGTH_SHORT).show()
                backPatientInformationScreen(view)
            }else{
                Toast.makeText(requireContext(), "Could not create report", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

    private fun backPatientInformationScreen(view: View){
        Navigation.findNavController(view).navigate(R.id.newPatientReportToPatientInformation)
    }

    private fun createReport(patientId: String): Report?{

        val inputActivyNewReport = binding.inputActivyNewReport.findViewById<EditText>(R.id.textInput).text
        val inputResumeNewReport = binding.inputResumeNewReport.text
        val inputConsultationNewReport = binding.inputConsultationNewReport.findViewById<EditText>(R.id.textInput).text
        val inputDayNewReport = binding.inputDayNewReport.findViewById<EditText>(R.id.textInput).text

        if(inputResumeNewReport.isNotEmpty() && inputConsultationNewReport.isNotEmpty()
            && inputDayNewReport.isNotEmpty()){

                try {
                    val reportCutDate = inputDayNewReport.split(" ")
                    var month = reportCutDate[0]
                    month = DataFormat().getMonth(month)
                    val day = reportCutDate[1].replace(",", "")
                    val year = reportCutDate[2]
                    val time = reportCutDate[3]
                    val dateReport = "$day/$month/$year"

                    return Report(
                        null,
                        inputActivyNewReport.split(", "),
                        inputResumeNewReport.toString(),
                        time,
                        inputConsultationNewReport.toString().toInt(),
                        dateReport,
                        patientId
                    )
                }catch (ex: Exception){
                    return Report()
                }
        }else{
            return null
        }
    }

}