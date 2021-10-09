package com.project.usychol.presenter.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentFullPsychologistBinding
import com.project.usychol.domain.entities.PLan
import com.project.usychol.viewModel.PLanViewModel

class FullPsychologistFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFullPsychologistBinding.inflate(inflater, container, false)

        val view: View = binding.root

        val viewModel = ViewModelProvider(this).get(PLanViewModel::class.java)

        val plan = "Full Psychologist"

        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val userId = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")

        binding.btnFullNext.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.fullToVirtual)
        }

        binding.btnFullSignPlan.setOnClickListener {
            viewModel.choosePsychologistPlan(userId!!, plan)

            Navigation.findNavController(view).navigate(R.id.fullToDashboard)
        }

        return view
    }
}