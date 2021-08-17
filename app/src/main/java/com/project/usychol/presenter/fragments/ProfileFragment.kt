package com.project.usychol.presenter.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.project.usychol.R
import com.project.usychol.databinding.FragmentProfileBinding
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.Psychologist
import com.project.usychol.viewModel.ProfileViewModel
import com.project.usychol.viewModel.viewModelFactory.ProfileViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences:SharedPreferences

    private lateinit var inputUserName: EditText
    private lateinit var inputUserEmail: EditText
    private lateinit var inputUserBirthday: EditText
    private lateinit var inputUserCRPNumber: EditText
    private lateinit var inputUserCPF: EditText
    private lateinit var inputUserPLanName: EditText
    private lateinit var inputUserPlanPayment: EditText
    private lateinit var inputUserPlanExpirationDay: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val view: View = binding.root

        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val gson = Gson()

        val psychologist = gson.fromJson(
            sharedPreferences.getString("userData", ""),
            Psychologist::class.java
        )

        val id = sharedPreferences.getInt(getString(R.string.salved_user_id_key), 0)

//        val viewModelFactory = ProfileViewModelFactory(id)
//        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)

        binding.apply {
            btnProfileBack.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.profileToDashboard)
            }

            btnEditProfile.setOnClickListener {
                val psychologists = getUserDataModel(id)
                if(psychologists != null){
                    psychologist.name = psychologists.name
                    psychologist.email = psychologists.email
                    psychologist.birthday = psychologists.birthday
                    psychologist.crpRegistration = psychologists.crpRegistration
                    psychologist.cpf = psychologists.cpf

                    val text = gson.toJson(psychologist)
                    sharedPreferences.edit {
                        putString("userData", text)
                    }
//                    viewModel.updateUserData(id, psychologist)
                }else{
                    Toast.makeText(activity, "fill in all data fields", Toast.LENGTH_SHORT).show()
                }
            }

            btnChangePlan.setOnClickListener {
                val plan = getUserPlanDataModel()
                if(plan != null){
                    psychologist.plan = plan
                    val text = gson.toJson(psychologist)
                    sharedPreferences.edit {
                        putString("userData", text)
                    }
//                    viewModel.updateUserPlanData(id, plan)
                }else{
                    Toast.makeText(activity, "fill in all plan fields", Toast.LENGTH_SHORT).show()
                }
            }
        }

          inputUserName = binding.inputUserName.findViewById(R.id.textInput)
          inputUserEmail = binding.inputUserEmail.findViewById(R.id.textInput)
          inputUserBirthday = binding.inputUserBirthday.findViewById(R.id.textInput)
          inputUserCRPNumber = binding.inputUserCRPNumber.findViewById(R.id.textInput)
          inputUserCPF = binding.inputUserCPF.findViewById(R.id.textInput)
          inputUserPLanName = binding.inputUserPLanName.findViewById(R.id.textInput)
          inputUserPlanPayment = binding.inputUserPlanPayment.findViewById(R.id.textInput)
          inputUserPlanExpirationDay = binding.inputUserPlanExpirationDay.findViewById(R.id.textInput)

//        viewModel.user.observe(viewLifecycleOwner, Observer { psychologist ->
            inputUserName.text = psychologist.name.toEditable()
            inputUserEmail.text = psychologist.email.toEditable()
            inputUserBirthday.text = psychologist.birthday.toEditable()
            inputUserCRPNumber.text = psychologist.crpRegistration.toEditable()
            inputUserCPF.text = psychologist.cpf.toEditable()
            inputUserPLanName.text = psychologist.plan!!.name.toEditable()
            inputUserPlanPayment.text = psychologist.plan!!.paymentMethod.toEditable()
            inputUserPlanExpirationDay.text = psychologist.plan!!.expirationDay.toEditable()
//        })

        return view
    }

    private fun Any.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this.toString())

    private fun getUserDataModel(id: Int): Psychologist? {
        return if(inputUserName.text.isNotEmpty() && inputUserEmail.text.isNotEmpty()
            && inputUserBirthday.text.isNotEmpty() && inputUserCRPNumber.text.isNotEmpty()
            && inputUserCPF.text.isNotEmpty()) {

            Psychologist(
                id,
                null,
                inputUserName.text.toString(),
                inputUserBirthday.text.toString(),
                inputUserCRPNumber.text.toString().toInt(),
                inputUserCPF.text.toString(),
                inputUserEmail.text.toString(),
                null,
                null
            )
        }else{
            null
        }
    }

    private fun getUserPlanDataModel(): PLan? {
        return if(inputUserPLanName.text.isNotEmpty() && inputUserPlanPayment.text.isNotEmpty()
            && inputUserPlanExpirationDay.text.isNotEmpty()) {

            PLan(
                inputUserPLanName.text.toString(),
                inputUserPlanPayment.text.toString(),
                inputUserPlanExpirationDay.text.toString()
            )
        }else{
            null
        }
    }
}
