package com.project.usychol.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentNewPatientActivityBinding
import com.project.usychol.domain.entities.Activy
import com.project.usychol.viewModel.NewPatientActivyViewModel

class NewPatientActivityFragment : Fragment() {

    private var _binding: FragmentNewPatientActivityBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()

        val activyNameOptions = resources.getStringArray(R.array.ddl_activy_name)
        val activyNameArrayAdapter = ArrayAdapter(requireContext(),
            R.layout.drop_down_text_options, activyNameOptions)
        binding.ddlNewActivity.setAdapter(activyNameArrayAdapter)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewPatientActivityBinding.inflate(inflater, container, false)

        val view: View = binding.root

        val newPatientActivyViewHolder = ViewModelProvider(this).get(NewPatientActivyViewModel ::class.java)

        binding.btnCreateActivy.setOnClickListener {
            val activy: Activy? = createActivy()

            if(activy != null){
                newPatientActivyViewHolder.createActivy(activy)
                backPatientInformationScreen()
            }else{
                Toast.makeText(activity, "fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNewActivyBack.setOnClickListener {
            backPatientInformationScreen()
        }

        return view
    }

    private fun backPatientInformationScreen(){
        Navigation.findNavController(binding.root).navigate(R.id.newPatientActivityToPatientInformation)
    }

    private  fun createActivy(): Activy?{

        val inputDayNewActivy = binding.inputDayNewActivy.findViewById<EditText>(R.id.textInput).text
        val inputDescriptionNewActivy = binding.inputDescriptionNewActivy.text
        val selectNewActivity = binding.selectNewActivity.editText!!.text

        return if(inputDayNewActivy.isNotEmpty() && inputDescriptionNewActivy.isNotEmpty()){

            Activy(
                null,
                inputDayNewActivy.toString(),
                inputDescriptionNewActivy.toString(),
                selectNewActivity.toString()
            )
        }else {
            null
        }
    }
}