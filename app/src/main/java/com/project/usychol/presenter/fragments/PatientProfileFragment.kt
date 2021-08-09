package com.project.usychol.presenter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentPatientProfileBinding
import com.project.usychol.databinding.FragmentRegisterPatientBinding

class PatientProfileFragment : Fragment() {

    private var _binding: FragmentPatientProfileBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()

        val patientClassOptions = resources.getStringArray(R.array.ddl_patient_class)
        val patientClassArrayAdapter = ArrayAdapter(requireContext(),
            R.layout.drop_down_text_options, patientClassOptions)
        binding.ddlPatientProfileClass.setAdapter(patientClassArrayAdapter)

        val maritalStatusOptions = resources.getStringArray(R.array.ddl_marital_status)
        val maritalStatusArrayAdapter = ArrayAdapter(requireContext(),
            R.layout.drop_down_text_options, maritalStatusOptions)
        binding.ddlPatientProfileMaritalStatus.setAdapter(maritalStatusArrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPatientProfileBinding.inflate(inflater, container, false)

        val view: View = binding.root

        binding.btnPatientProfileBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.patientProfileToPatientInformation)
        }


        return view
    }
}