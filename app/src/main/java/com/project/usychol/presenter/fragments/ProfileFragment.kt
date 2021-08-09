package com.project.usychol.presenter.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.Placeholder
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.project.usychol.R
import com.project.usychol.databinding.FragmentProfileBinding
import com.project.usychol.viewModel.ProfileViewModel
import com.project.usychol.viewModel.viewModelFactory.ProfileViewModelFactory

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentProfileBinding.inflate(inflater, container, false)

        val view: View = binding.root

        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val id = sharedPreferences.getInt(getString(R.string.salved_user_id_key), 0)

        val viewModelFactory = ProfileViewModelFactory(id)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)

        var inputUserName = binding.inputUserName
        val inputUserEmail = binding.inputUserEmail.findViewById<EditText>(R.id.textInput)
        val inputUserBirthday = binding.inputUserBirthday.findViewById<EditText>(R.id.textInput)
        val inputUserCRPNumber = binding.inputUserCRPNumber.findViewById<EditText>(R.id.textInput)
        val inputUserCPF = binding.inputUserCPF.findViewById<EditText>(R.id.textInput)

        val inputUserPLanName = binding.inputUserPLanName.findViewById<EditText>(R.id.textInput)
        val inputUserPlanPayment = binding.inputUserPlanPayment.findViewById<EditText>(R.id.textInput)
        val inputUserPlanExpirationDay = binding.inputUserPlanExpirationDay.findViewById<EditText>(R.id.textInput)

        binding.btnProfileBack.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.profileToDashboard)
        }

        viewModel.user.observe(viewLifecycleOwner, Observer { psychologist ->
            println(psychologist)
        })

        return view
    }

}