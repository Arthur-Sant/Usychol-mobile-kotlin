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
import com.google.firebase.auth.FirebaseAuth
import com.project.usychol.R
import com.project.usychol.databinding.FragmentRegisterPatientBinding
import com.project.usychol.domain.entities.Patient
import com.project.usychol.shared.DataFormat
import com.project.usychol.viewModel.RegisterPatientViewModel

class RegisterPatientFragment : Fragment() {

    private var _binding: FragmentRegisterPatientBinding? = null
    private val binding get() = _binding!!


    override fun onResume() {
        super.onResume()

        val patientClassOptions = resources.getStringArray(R.array.ddl_patient_class)
        val patientClassArrayAdapter = ArrayAdapter(requireContext(),
            R.layout.drop_down_text_options, patientClassOptions)
        binding.ddlPatientClass.setAdapter(patientClassArrayAdapter)

        val maritalStatusOptions = resources.getStringArray(R.array.ddl_marital_status)
        val maritalStatusArrayAdapter = ArrayAdapter(requireContext(),
            R.layout.drop_down_text_options, maritalStatusOptions)
        binding.ddlMaritalStatus.setAdapter(maritalStatusArrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterPatientBinding.inflate(inflater, container, false)

        val view: View = binding.root

        val viewModel = ViewModelProvider(this).get(RegisterPatientViewModel::class.java)

        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()

//        val userId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")!!

        binding.btnRegisterPatientBack.setOnClickListener {
            backToDashboardScreen(view)
        }

        binding.btnCreatePatient.setOnClickListener {
            val patient = registerPatient(userId)

            if(patient?.name == ""){
                Toast.makeText(
                    requireContext(),
                    "Insira a data do report do modo que foi proposto, mes em " +
                            "ingles, dia e ano",
                    Toast.LENGTH_LONG
                ).show()

            } else if(patient != null){
                viewModel.registerPatient(patient)
            }else{
                Toast.makeText(activity, "fill in all fields", Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.patientId.observe(viewLifecycleOwner, Observer { id ->
            if(id != null){
                sharedPreferences.edit {
                    putString(getString(R.string.salved_patient_id_key), id)
                }
                backToDashboardScreen(view)
                Toast.makeText(requireContext(), "Patient registered successfully", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Unable to register user", Toast.LENGTH_SHORT).show()
            }
        })
        return view
    }

    private fun registerPatient(userId: String): Patient? {

        val inputPatientName = binding.inputPatientName.findViewById<EditText>(R.id.textInput).text
        val inputPatientBirthday = binding.inputPatientBirthday.findViewById<EditText>(R.id.textInput).text
        val selectPatientClass = binding.selectPatientClass.editText!!.text
        val inputPatientMotherName = binding.inputPatientMotherName.findViewById<EditText>(R.id.textInput).text
        val inputPatientFatherName = binding.inputPatientFatherName.findViewById<EditText>(R.id.textInput).text
        val selectMaritalPatientClass = binding.selectMaritalPatientStatus.editText!!.text

        if(!inputPatientName.isNullOrEmpty() && !inputPatientBirthday.isNullOrEmpty()
        && !selectPatientClass.isNullOrEmpty() && !inputPatientMotherName.isNullOrEmpty()
            && !inputPatientFatherName.isNullOrEmpty() && !selectMaritalPatientClass.isNullOrEmpty()) {

                try {
                    val reportCutDate = inputPatientBirthday.split(" ")
                    var month = reportCutDate[0]
                    month = DataFormat().getMonth(month)
                    val day = reportCutDate[1].replace(",", "")
                    val year = reportCutDate[2]

                    return Patient(
                        null,
                        inputPatientName.toString(),
                        0,
                        selectPatientClass.toString(),
                        inputPatientMotherName.toString(),
                        "",
                        inputPatientFatherName.toString(),
                        selectMaritalPatientClass.toString(),
                        inputPatientBirthday.toString(),
                        userId,
                        null
                    )
                }catch (ex: Exception){
                    return Patient()
                }
        }else{
            return null
        }
    }

    private fun backToDashboardScreen(view: View){
        Navigation.findNavController(view).navigate(R.id.registerPatientToDashboard)
    }
}