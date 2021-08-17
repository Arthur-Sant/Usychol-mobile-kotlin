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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.project.usychol.R
import com.project.usychol.databinding.FragmentSignupBinding
import com.project.usychol.domain.entities.Psychologist
import com.project.usychol.viewModel.SignupViewModel

class SignupFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding

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
            val psychologist: Psychologist? = registerPsychologist()

            if(psychologist != null) {

                val gson = Gson()

                val text = gson.toJson(psychologist)

                println(text)

                sharedPreferences.edit {
                    putInt(getString(R.string.salved_user_id_key), psychologist.id!!)
                    putString("userData", text)
                }

//                viewModel.registerPsychologist(psychologist)

                Navigation.findNavController(view).navigate(R.id.signupToApproval)
            }else{
                Toast.makeText(activity, "fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvSiginupAlreadyAccount.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.signupToSignin)
        }

        return view
    }

    private fun registerPsychologist(): Psychologist? {

        val psychologistName = binding.inputName.findViewById<EditText>(R.id.textInput).text
        val psychologistBirthday = binding.inputBirthday.findViewById<EditText>(R.id.textInput).text
        val psychologistCrp = binding.inputIdNumber.findViewById<EditText>(R.id.textInput).text
        val psychologistCpf = binding.inputDocument.findViewById<EditText>(R.id.textInput).text
        val psychologistEmail = binding.inputEmail.findViewById<EditText>(R.id.textInput).text
        val psychologistPassword = binding.inputPassword.findViewById<EditText>(R.id.textInput).text

        if(psychologistName.isNotEmpty() && psychologistBirthday.isNotEmpty()
            && psychologistCpf.isNotEmpty() && psychologistCrp.isNotEmpty()
            && psychologistEmail.isNotEmpty() && psychologistPassword.isNotEmpty()) {

                return Psychologist(
                    null,
                    null,
                    psychologistName.toString(),
                    psychologistBirthday.toString(),
                    psychologistCrp.toString().toInt(),
                    psychologistCpf.toString(),
                    psychologistEmail.toString(),
                    psychologistPassword.toString(),
                    null
                )
        }else{
            return null

        }
    }

}