package com.project.usychol.presenter.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentPatientProfileBinding
import com.project.usychol.domain.entities.Patient
import com.project.usychol.domain.entities.Report
import com.project.usychol.implementations.PatientImplementation
import com.project.usychol.viewModel.PatientProfileViewModel

class PatientProfileFragment : Fragment() {

    private lateinit var binding: FragmentPatientProfileBinding

    private lateinit var patientProfileViewModel: PatientProfileViewModel

    private lateinit var inputName: EditText
    private lateinit var inputBirthday: EditText
    private lateinit var selectClass: EditText
    private lateinit var inputMotherName: EditText
    private lateinit var inputFatherName: EditText
    private lateinit var selectMaritalStatus: EditText
    private var patientSummary: String? = null
    private var arrayReport: ArrayList<Report>? = null

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

        binding = FragmentPatientProfileBinding.inflate(inflater, container, false)

        val view: View = binding.root

         val sharedPreferences = requireActivity().getSharedPreferences(
             getString(R.string.preference_file_key),
             Context.MODE_PRIVATE
         )

        val id = sharedPreferences.getString(getString(R.string.salved_patient_id_key), "")!!
        val userId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")!!

//        patientProfileViewModel = ViewModelProvider(this).get(PatientProfileViewModel::class.java)

//        patientProfileViewModel.getPatientData(userId, id)

        startObservationPatientData(userId, id)

        binding.btnPatientProfileBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.patientProfileToPatientInformation)
        }

        binding.btnEditPatientProfile.setOnClickListener {
            val patient = Patient(
                id,
                null,
                inputName.text.toString(),
                4,
                selectClass.text.toString(),
                inputMotherName.text.toString(),
                patientSummary.toString(),
                inputFatherName.text.toString(),
                selectMaritalStatus.text.toString(),
                inputBirthday.text.toString(),
                userId,
                arrayReport
            )

            PatientImplementation().update(userId, id, patient){}
        }

        return view
    }

    private fun Any.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this.toString())

    fun startObservationPatientData(userId: String, id: String){
        val tvName = binding.tvPatientProfileName
        inputName = binding.inputPatientProfileName.findViewById(R.id.textInput)
        inputBirthday = binding.inputPatientProfileBirthday.findViewById(R.id.textInput)
        selectClass = binding.selectPatientProfileClass.editText!!
        inputMotherName = binding.inputPatientProfileMotherName.findViewById(R.id.textInput)
        inputFatherName = binding.inputPatientProfileFatherName.findViewById(R.id.textInput)
        selectMaritalStatus = binding.selectMaritalPatientProfileStatus.editText!!

//        patientProfileViewModel.patient.observe(viewLifecycleOwner, Observer { patient ->
          PatientImplementation().findById(userId, id){ patient ->
              if(patient != null) {
                  tvName.text = patient.name
                  patientSummary = patient.patientSummary
                  inputName.text = patient.name.toEditable()
                  inputBirthday.text = patient.age?.toEditable()
                  selectClass.text = patient.patientClass.toEditable()
                  inputMotherName.text = patient.motherName.toEditable()
                  inputFatherName.text = patient.fatherName.toEditable()
                  selectMaritalStatus.text = patient.maritalStatus.toEditable()
                  arrayReport = patient.reports
              }
        }

    }
}