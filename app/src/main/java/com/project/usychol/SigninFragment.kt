package com.project.usychol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.project.usychol.databinding.FragmentSigninBinding

class SigninFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSigninBinding.inflate(inflater, container, false)

        val view: View = binding.root

        binding.createAccount.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.signinToSignup)
        }

        return view
    }
}