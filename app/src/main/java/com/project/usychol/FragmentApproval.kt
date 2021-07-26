package com.project.usychol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.project.usychol.databinding.FragmentApprovalBinding

class FragmentApproval : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentApprovalBinding.inflate(inflater, container, false)

        val view: View = binding.root

        binding.textView2.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.approvalToVirtualManager)
        }

        return view
    }
}