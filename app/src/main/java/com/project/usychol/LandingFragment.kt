package com.project.usychol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.project.usychol.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLandingBinding.inflate(inflater, container, false)

        val view: View = binding.root

        binding.btnSignin.setOnClickListener {
            println("to na landing ainda")
            Navigation.findNavController(view).navigate(R.id.landingToSignin)
        }

        return view
    }
}