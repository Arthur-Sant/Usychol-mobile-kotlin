package com.project.usychol.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import java.text.SimpleDateFormat
import java.util.*

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

        sharedPreferences = requireActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

//        val userId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")!!

        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()

        dashboardViewModel.getAllPatients(userId)
        dashboardViewModel.getAllUserReminder()

        startPatientObservation()
        startUserReminderObservation()

        binding.btnRegisterPatient.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.dashboardToRegisterPatient)
        }

        binding.btnUserProfile.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.dashboardToProfile)
        }

        binding.btnCreateReminder.setOnClickListener {
            val reminder = createReminder()
            if(reminder != null){
                dashboardViewModel.createUserReminder(reminder)
            }else{
                Toast.makeText(requireContext(), "fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        dashboardViewModel.messageCreate.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            binding.etUserReminder.setText("")
            dashboardViewModel.getAllUserReminder()
        })

        return view

    }

    private fun startPatientObservation(){
        dashboardViewModel.listPatient.observe(viewLifecycleOwner, Observer { listPatient ->
            if(listPatient.isNotEmpty()){
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

    private fun startUserReminderObservation(){
        dashboardViewModel.listUserReminder.observe(viewLifecycleOwner, Observer { listReminder ->
            if (listReminder != null) {
                renderListUserReminder(listReminder)
            }
        })
    }

    private fun renderListUserReminder(listReminder: List<Reminder>){
        val reminderAdapter = ReminderAdapter(requireContext(), listReminder)

        binding.recyclerViewUserReminder.apply {
            adapter = reminderAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    fun createReminder(): Reminder?{
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.HOUR, 1)
        val format = SimpleDateFormat("HH:mm")
        val reminderTitle = binding.etUserReminder.text.toString()
        val userid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val timeStart = format.format(Date())
        val timeEnd = format.format(calendar.time)

        return if(reminderTitle.isNotEmpty()){
            Reminder(
                null,
                reminderTitle,
                "$timeStart PM",
                "$timeEnd PM",
                userid
            )
        }else{
            null
        }
    }
}