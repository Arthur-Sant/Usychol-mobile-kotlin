package com.project.usychol.view.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentApprovalBinding

class FragmentApproval : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentApprovalBinding.inflate(inflater, container, false)

        val view: View = binding.root

        Handler().postDelayed({
            Navigation.findNavController(view).navigate(R.id.approvalToSignin)
        }, 2000)

        return view
    }
}
