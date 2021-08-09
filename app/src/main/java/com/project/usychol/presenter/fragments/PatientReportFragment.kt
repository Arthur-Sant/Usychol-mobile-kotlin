package com.project.usychol.presenter.fragments

import android.os.Bundle
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
import com.project.usychol.viewModel.PatientReportViewModel

class PatientReportFragment : Fragment() {

    private lateinit var binding: FragmentPatientReportBinding
    private lateinit var patientReportViewModel: PatientReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPatientReportBinding.inflate(inflater, container, false)

        val view: View = binding.root

        patientReportViewModel = ViewModelProvider(this).get(PatientReportViewModel::class.java)

        patientReportViewModel.getAllActivy()

        startPatientObservation()

        binding.btnPatientProfileBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.patientReportToPatientInformation)
        }

        return view
    }

    private fun startPatientObservation(){
        patientReportViewModel.listActivy.observe(viewLifecycleOwner, Observer { listActivy ->
            if(listActivy != null){
                renderListActivy(listActivy)
            }
        })
    }

    private fun renderListActivy(listActivy: List<Activy>){
        val activyAdapter = ActivyAdapter(requireContext(),  listActivy)
        binding.recyclerViewReportActivy.apply {
            adapter = activyAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

}