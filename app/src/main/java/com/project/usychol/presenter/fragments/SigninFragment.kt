package com.project.usychol.presenter.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentSigninBinding
import com.project.usychol.viewModel.SiginViewModel

class SigninFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSigninBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this).get(SiginViewModel::class.java)

        val view: View = binding.root

        val shared = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)

        val signinEmail = binding.etSigninEmail.findViewById<EditText>(R.id.textInput).text
        val signinPassword = binding.etSigninPassword.findViewById<EditText>(R.id.textInput).text

        binding.createAccount.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.signinToSignup)
        }

        binding.btnSignin.setOnClickListener{
            viewModel.loginUser(signinEmail.toString(), signinPassword.toString())
        }

        viewModel.loginSituation.observe(viewLifecycleOwner, Observer { situation ->
            when(situation){
                "logged" -> Navigation.findNavController(view).navigate(R.id.signinToDashboard)
                "logged in but no plan" -> Navigation.findNavController(view).navigate(R.id.signinToVirtualManager)
                else -> {
                    Toast.makeText(activity, "Incorrect Username or Password", Toast.LENGTH_SHORT).show()
                }
             }
        })

        viewModel.userId.observe(viewLifecycleOwner, Observer { id ->
            shared.edit {
                putInt("id", id)
            }
        })

        return view
    }
}