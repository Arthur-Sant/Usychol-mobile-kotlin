package com.project.usychol.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.project.usychol.R
import com.project.usychol.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {

    private lateinit var binding: FragmentLandingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingBinding.inflate(inflater, container, false)

        val view: View = binding.root

        binding.btnSignin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.landingToSignin)
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        val currentUser = FirebaseAuth.getInstance().currentUser

        if(currentUser != null){
            Navigation.findNavController(binding.root).navigate(R.id.landingToDashboard)
        }
    }
}