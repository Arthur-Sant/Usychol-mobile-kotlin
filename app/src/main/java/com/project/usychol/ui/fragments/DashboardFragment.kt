package com.project.usychol.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val view: View = binding.root

        binding.btnRegisterPatient.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.dashboardToRegisterPatient)
        }

        binding.btnUserProfile.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.dashboardToProfile)
        }

        return view

    }
}