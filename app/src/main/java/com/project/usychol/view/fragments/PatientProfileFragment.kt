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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.project.usychol.R
import com.project.usychol.databinding.FragmentPatientProfileBinding
import com.project.usychol.domain.entities.Patient
import com.project.usychol.shared.DataFormat
import com.project.usychol.viewModel.PatientProfileViewModel

class PatientProfileFragment : Fragment() {

    private lateinit var binding: FragmentPatientProfileBinding

    private lateinit var viewModel: PatientProfileViewModel

    private lateinit var inputName: EditText
    private lateinit var inputBirthday: EditText
    private lateinit var selectClass: EditText
    private lateinit var inputMotherName: EditText
    private lateinit var inputFatherName: EditText
    private lateinit var selectMaritalStatus: EditText
    private var patientSummary: String? = null

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
//        val userId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")!!

        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()

        viewModel = ViewModelProvider(this).get(PatientProfileViewModel::class.java)

        viewModel.getPatientData(id)

        startObservationPatientData()

        binding.btnPatientProfileBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.patientProfileToPatientInformation)
        }

        binding.btnEditPatientProfile.setOnClickListener {
            val patient = updatePatient()

            if(patient?.name == ""){
                Toast.makeText(
                    requireContext(),
                    "Insira a data do report do modo que foi proposto, mes em " +
                            "ingles, dia e ano",
                    Toast.LENGTH_LONG
                ).show()
            }else if(patient != null){
                patient.id = id
                patient.fromUser= userId
                viewModel.updatePatientData(patient)
            }else{
                Toast.makeText(requireContext(), "fill all fields", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.updatePatient.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })

        return view
    }

    fun updatePatient(): Patient?{
        if(inputName.text.toString().isNotEmpty()
            && inputBirthday.text.toString().isNotEmpty()
            && inputFatherName.text.toString().isNotEmpty()
            && inputMotherName.text.toString().isNotEmpty()){

            try{
                val reportCutDate = inputBirthday.text.split(" ")
                var month = reportCutDate[0]
                month = DataFormat().getMonth(month)
                val day = reportCutDate[1].replace(",", "")
                val year = reportCutDate[2]

                return Patient(
                    null,
                    inputName.text.toString(),
                    4,
                    selectClass.text.toString(),
                    inputMotherName.text.toString(),
                    patientSummary.toString(),
                    inputFatherName.text.toString(),
                    selectMaritalStatus.text.toString(),
                    inputBirthday.text.toString(),
                    "",
                    null
                )
            }catch (ex: Exception){
                return Patient()
            }
        }else{
            return null
        }
    }

    fun startObservationPatientData(){
        val tvName = binding.tvPatientProfileName
        inputName = binding.inputPatientProfileName.findViewById(R.id.textInput)
        inputBirthday = binding.inputPatientProfileBirthday.findViewById(R.id.textInput)
        selectClass = binding.selectPatientProfileClass.editText!!
        inputMotherName = binding.inputPatientProfileMotherName.findViewById(R.id.textInput)
        inputFatherName = binding.inputPatientProfileFatherName.findViewById(R.id.textInput)
        selectMaritalStatus = binding.selectMaritalPatientProfileStatus.editText!!

        viewModel.patient.observe(viewLifecycleOwner, Observer { patient ->
            if (patient != null) {
                tvName.text = patient.name
                patientSummary = patient.patientSummary
                inputName.setText(patient.name)
                inputBirthday.setText(patient.age)
                selectClass.setText(patient.patientClass)
                inputMotherName.setText(patient.motherName)
                inputFatherName.setText(patient.fatherName)
                selectMaritalStatus.setText(patient.maritalStatus)
            }
        })
    }
}