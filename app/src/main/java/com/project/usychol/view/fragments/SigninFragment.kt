package com.project.usychol.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
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

//        val shared = requireActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val signinEmail = binding.etSigninEmail.findViewById<EditText>(R.id.textInput).text
        val signinPassword = binding.etSigninPassword.findViewById<EditText>(R.id.textInput).text

        binding.createAccount.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.signinToSignup)
        }

        binding.btnSignin.setOnClickListener{
            if(signinEmail.toString().isNotEmpty()
                && signinPassword.toString().isNotEmpty()) {

                viewModel.loginUser(signinEmail.toString(), signinPassword.toString())
            }else{
                Toast.makeText(requireContext(), "fill all in fields", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.loginSituation.observe(viewLifecycleOwner, Observer { situation ->
            if(situation == "logged") {
                Navigation.findNavController(view).navigate(R.id.signinToDashboard)
            }else{
                Toast.makeText(activity, situation, Toast.LENGTH_SHORT).show()
            }
        })

//        viewModel.userId.observe(viewLifecycleOwner, Observer { id ->
//            shared.edit {
//                putString(getString(R.string.salved_user_id_key), id)
//            }
//        })

        return view
    }
}