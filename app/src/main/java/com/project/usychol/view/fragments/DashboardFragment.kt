package com.project.usychol.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.project.usychol.R
import com.project.usychol.adapters.PatientAdapter
import com.project.usychol.adapters.ReminderAdapter
import com.project.usychol.databinding.FragmentDashboardBinding
import com.project.usychol.domain.entities.Patient
import com.project.usychol.domain.entities.Reminder
import com.project.usychol.viewModel.DashboardViewModel

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var binding: FragmentDashboardBinding

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val view: View = binding.root

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

//        sharedPreferences = requireActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

//        val userId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")!!

        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()

        dashboardViewModel.getAllPatients(userId)
        dashboardViewModel.getAllUserReminder()

        startPatientObservation(userId)
        startuserReminderObservation()

        binding.btnRegisterPatient.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.dashboardToRegisterPatient)
        }

        binding.btnUserProfile.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.dashboardToProfile)
        }

        return view

    }

    private fun startPatientObservation(id: String){
        dashboardViewModel.listPatient.observe(viewLifecycleOwner, Observer { listPatient ->
            if(listPatient.isNotEmpty()){
                println(listPatient)
                renderListPatient(listPatient)
            }
        })
    }

    private fun renderListPatient(listPatient: List<Patient>){
        val patientAdapter = PatientAdapter(requireContext(), listPatient, fun (patientId: String){

            sharedPreferences.edit {
                putString(getString(R.string.salved_patient_id_key), patientId)
            }

            Navigation.findNavController(binding.root).navigate(R.id.dashboardToPatientInformation)
        })

        binding.recyclerViewListPatient.apply {
            adapter = patientAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun startuserReminderObservation(){
        dashboardViewModel.listUserReminder.observe(viewLifecycleOwner, Observer { listReminder ->
            if (listReminder != null) {
                renderListuserReminder(listReminder)
            }
        })
    }

    private fun renderListuserReminder(listReminder: List<Reminder>){
        val reminderAdapter = ReminderAdapter(requireContext(), listReminder)

        binding.recyclerViewUserReminder.apply {
            adapter = reminderAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }
}