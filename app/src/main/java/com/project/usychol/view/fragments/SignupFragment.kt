package com.project.usychol.view.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.project.usychol.R
import com.project.usychol.databinding.FragmentSignupBinding
import com.project.usychol.domain.entities.User
import com.project.usychol.viewModel.SignupViewModel


class SignupFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        val view: View = binding.root

        val sharedPreferences = requireActivity()
            .getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        binding.btnSignup.setOnClickListener {
            val user: User? = registerUser()

            if(user != null) {
                viewModel.registerUser(user)
            }else{
                Toast.makeText(activity, "fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvSiginupAlreadyAccount.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.signupToSignin)
        }

        viewModel.messageError.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(),  message, Toast.LENGTH_SHORT ).show()

            if(message.contains("Successfully")){
                Navigation.findNavController(view).navigate(R.id.signupToApproval)
            }
        })

        return view
    }

    private fun registerUser(): User? {

        val userName = binding.inputName.findViewById<EditText>(R.id.textInput).text
        val userBirthday = binding.inputBirthday.findViewById<EditText>(R.id.textInput).text
        val userCrp = binding.inputIdNumber.findViewById<EditText>(R.id.textInput).text
        val userCpf = binding.inputDocument.findViewById<EditText>(R.id.textInput).text
        val userEmail = binding.inputEmail.findViewById<EditText>(R.id.textInput).text
        val userPassword = binding.inputPassword.findViewById<EditText>(R.id.textInput).text

        if(userName.isNotEmpty() && userBirthday.isNotEmpty()
            && userCpf.isNotEmpty() && userCrp.isNotEmpty()
            && userEmail.isNotEmpty() && userPassword.isNotEmpty()) {

                return User(
                    null,
                    userName.toString(),
                    userCrp.toString(),
                    userCpf.toString(),
                    userEmail.toString(),
                    userPassword.toString(),
                    userBirthday.toString(),
                    null
                )
        }else{
            return null
        }
    }

}