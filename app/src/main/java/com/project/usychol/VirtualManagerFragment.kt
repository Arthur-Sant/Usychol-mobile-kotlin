package com.project.usychol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.project.usychol.databinding.FragmentVirtualManagerBinding

class VirtualManagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVirtualManagerBinding.inflate(inflater, container, false)

        val view: View = binding.root

        binding.btnVirtualSignPlan.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.virtualToDashboard)
        }

        binding.btnVirtualNext.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.virtualToDigital)
        }

        return view
    }

}