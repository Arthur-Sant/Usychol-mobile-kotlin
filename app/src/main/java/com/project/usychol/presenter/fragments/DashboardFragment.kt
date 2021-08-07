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
import androidx.recyclerview.widget.RecyclerView
import com.project.usychol.R
import com.project.usychol.adapters.PatientAdapter
import com.project.usychol.databinding.FragmentDashboardBinding
import com.project.usychol.domain.entities.Patient
import com.project.usychol.viewModel.DashboardViewModel

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val view: View = binding.root

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        startPatientObservation()

        binding.btnRegisterPatient.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.dashboardToRegisterPatient)
        }

        binding.btnUserProfile.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.dashboardToProfile)
        }

        return view

    }

    private fun startPatientObservation(){
        dashboardViewModel.listPatient.observe(this, Observer { listPatient ->
            if(listPatient.isNotEmpty()){
                renderListPatient(listPatient)
            }

        })
    }

    private fun renderListPatient(listPatient: ArrayList<Patient>){
        binding.recyclerViewListPatient.apply {
            adapter = PatientAdapter(context, listPatient)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }
}