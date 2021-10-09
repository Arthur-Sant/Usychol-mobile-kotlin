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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.project.usychol.R
import com.project.usychol.databinding.FragmentProfileBinding
import com.project.usychol.domain.entities.User
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.viewModel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var viewModel: ProfileViewModel

    private lateinit var sharedPreferences:SharedPreferences

    private lateinit var inputUserName: EditText
    private lateinit var inputUserEmail: EditText
    private lateinit var inputUserBirthday: EditText
    private lateinit var inputUserCRPNumber: EditText
    private lateinit var inputUserCPF: EditText
    private lateinit var inputUserPLanName: EditText
    private lateinit var inputUserPlanPayment: EditText
    private lateinit var inputUserPlanExpirationDay: EditText
    private lateinit var password: String
    private lateinit var plan: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val view: View = binding.root

        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val id = sharedPreferences.getString(getString(R.string.salved_user_id_key), "")!!

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
//        viewModel.getDataFromPsychologist(id)

        startObservationUserData(id)

        binding.apply {
            btnProfileBack.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.profileToDashboard)
            }

            btnEditProfile.setOnClickListener {
                val user = getUserDataModel(id)
                if(user != null){
//                    viewModel.updateUserData(user)
                    UserImplementation().update(id, user){}
                }else{
                    Toast.makeText(activity, "fill in all data fields", Toast.LENGTH_SHORT).show()
                }
            }

            btnChangePlan.setOnClickListener {
                val plan = getUserPlanDataModel()
                if(plan != null){
                    viewModel.updateUserPlanData(id, plan)
                }else{
                    Toast.makeText(activity, "fill in all plan fields", Toast.LENGTH_SHORT).show()
                }
            }

            btnDeleteUser.setOnClickListener {
                UserImplementation().delete(id){}
                Navigation.findNavController(view).navigate(R.id.profileFragmentToSigninFragment)
            }
        }

        return view
    }

    private fun Any.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this.toString())

    private fun getUserDataModel(id: String): User? {
        return if(inputUserName.text.isNotEmpty()
            && inputUserEmail.text.isNotEmpty()
            && inputUserBirthday.text.isNotEmpty()
            && inputUserCRPNumber.text.isNotEmpty()
            && inputUserCPF.text.isNotEmpty()) {

            User(
                id,
                inputUserName.text.toString(),
                inputUserCRPNumber.text.toString(),
                inputUserCPF.text.toString(),
                inputUserEmail.text.toString(),
                password,
                inputUserBirthday.text.toString(),
                plan
            )
        }else{
            null
        }
    }

    private fun getUserPlanDataModel(): String? {
        return if(inputUserPLanName.text.isNotEmpty()){
//            && inputUserPlanPayment.text.isNotEmpty()
//            && inputUserPlanExpirationDay.text.isNotEmpty()) {

//            PLan(
                inputUserPLanName.text.toString()
//                inputUserPlanPayment.text.toString(),
//                inputUserPlanExpirationDay.text.toString()
//            )
        }else{
            null
        }
    }

    private fun startObservationUserData(id: String){
        inputUserName = binding.inputUserName.findViewById(R.id.textInput)
        inputUserEmail = binding.inputUserEmail.findViewById(R.id.textInput)
        inputUserBirthday = binding.inputUserBirthday.findViewById(R.id.textInput)
        inputUserCRPNumber = binding.inputUserCRPNumber.findViewById(R.id.textInput)
        inputUserCPF = binding.inputUserCPF.findViewById(R.id.textInput)
        inputUserPLanName = binding.inputUserPLanName.findViewById(R.id.textInput)
        inputUserPlanPayment = binding.inputUserPlanPayment.findViewById(R.id.textInput)
        inputUserPlanExpirationDay = binding.inputUserPlanExpirationDay.findViewById(R.id.textInput)
        val tvUserCardSubtitle = binding.tvUserCardSubtitle

//        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
        UserImplementation().findById(id){ user ->
            if(user != null) {
                inputUserName.text = user.name.toEditable()
                inputUserEmail.text = user.email.toEditable()
                inputUserBirthday.text = user.age.toEditable()
                inputUserCRPNumber.text = user.crp.toEditable()
                inputUserCPF.text = user.cpf.toEditable()
                password = user.password.toString()
                plan = user.plan.toString()
                inputUserPLanName.text = user.plan!!.toEditable()
                inputUserPlanPayment.text = "Creadit Card".toEditable()
                inputUserPlanExpirationDay.text = "07".toEditable()
                tvUserCardSubtitle.text = "USychol Plan: ${user.plan}"
            }
        }
    }
}
