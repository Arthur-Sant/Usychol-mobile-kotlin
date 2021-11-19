package com.project.usychol.viewModel

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.Patient
import com.project.usychol.domain.entities.User
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.useCases.UserUseCase

class ProfileViewModel() : ViewModel(){

    val database = Firebase.firestore

    private val userDAO = UserImplementation()
    private val userRepository = UserRepository(userDAO)
    private val userUseCases = UserUseCase(userRepository)

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
    get () = _user

    private var _messageError = MutableLiveData<String>()
    val messageError: LiveData<String>
    get() = _messageError

    fun getDataFromPsychologist(id: String){
        Thread {
            userUseCases.findById(id) { user ->
                if (user != null) {
                    _user.postValue(user)
                }
            }
        }.start()
    }

    fun updateUserData(user: User){
        Thread {
            userUseCases.updateUser(user) { error ->
                if(error != null){
                    _messageError.postValue(error)
                }else{
                    _messageError.postValue("User updated successfully")
                }
            }
        }.start()
    }

    fun updateUserPlanData(userId: String, plan: String){
        Thread {
            userUseCases.choosePlan(userId, plan) {
                if(it) {
                    _messageError.postValue("Plan updated successfully")
                }
            }
        }.start()
    }

    fun deleteUser(userId: String){
        Thread{
            userUseCases.deleteUser(userId){
                if(it != null){
                    _messageError.postValue(it)
                }else{
                    _messageError.postValue("User deleted successfully")
                }
            }
        }.start()

        deleteReminder(userId)
        deletePatientsAndReports(userId)

    }

    private fun deleteReminder(userId: String){
        val batch = database.batch()
        Thread{
            database.collection("reminders")
                .whereEqualTo("fromUser", userId)
                .get()
                .addOnSuccessListener {
                    val snapShots = it.documents

                    snapShots.forEach { snapShot ->
                        batch.delete(snapShot.reference)
                    }

                    batch.commit()
                        .addOnSuccessListener {
                            println("reminders deleted successfully")
                        }
                        .addOnFailureListener {
                            println("could not delete reminders")
                        }
                }

        }.start()
    }

    private fun deletePatientsAndReports(userId: String){
            val batch = database.batch()
            Thread{
                database.collection("patients")
                    .whereEqualTo("fromUser", userId)
                    .get()
                    .addOnSuccessListener {
                        val snapShots = it.documents

                        snapShots.forEach { snapShot ->
                            println(snapShot.id)
                            deleteReports(snapShot.id)
                            batch.delete(snapShot.reference)
                        }

                        batch.commit()
                            .addOnSuccessListener {
                                println("patients deleted successfully")
                            }
                            .addOnFailureListener {
                                println("could not delete patients")
                            }
                    }

            }.start()
    }

    private fun deleteReports(patientId: String){
        val batch = database.batch()
        database.collection("reports")
            .whereEqualTo("fromPatient", patientId)
            .get()
            .addOnSuccessListener {
                val snapShots = it.documents

                snapShots.forEach { snapShot ->
                    batch.delete(snapShot.reference)
                }

                batch.commit()
                    .addOnSuccessListener {
                        println("reports deleted successfully")
                    }
                    .addOnFailureListener {
                        println("could not delete reports")
                    }
            }
    }
}