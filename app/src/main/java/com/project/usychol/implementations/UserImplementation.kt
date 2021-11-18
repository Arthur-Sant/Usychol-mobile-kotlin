package com.project.usychol.implementations

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.usychol.data.dao.UserDAO
import com.project.usychol.domain.entities.User

class UserImplementation(): UserDAO {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.firestore
    private val collectionPath = "users"
    private val uid = mAuth.currentUser?.uid.toString()

    override fun findById(id: String, returnUser: (User?) -> Unit) {
        database.collection(collectionPath).document(uid).get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                    returnUser(user)
            }
            .addOnFailureListener {
                returnUser(null)
            }
    }

    override fun update(id: String, body: User, returnError: (String?) -> Unit) {
        database.collection(collectionPath).document(uid).set(body)
            .addOnSuccessListener {
                returnError(null)
            }
            .addOnFailureListener {
                returnError(it.localizedMessage)
            }
    }

    override fun create(body: User, returnError: (String?) -> Unit) {
        fun reverseRegistration(error: String){
            mAuth.currentUser?.delete()
            returnError(error)
        }

        if(mAuth != null){
            mAuth.createUserWithEmailAndPassword(body.email, body.password!!)
                .addOnSuccessListener {
                    val uid = it.user?.uid.toString()
                    body.id = uid

                    if(uid.isNotEmpty()){
                        database.collection(collectionPath).document(uid).set(body)
                            .addOnSuccessListener {
                                returnError(null)
                            }
                            .addOnFailureListener { error ->
                                reverseRegistration(error.localizedMessage!!)
                            }
                    }else{
                        reverseRegistration("User not exists")
                    }
                }

                .addOnFailureListener {
                    reverseRegistration(it.localizedMessage!!)
                }
        }
    }

    override fun delete(id: String, returnError: (String?) -> Unit) {
        try {
            database.collection(collectionPath).document(uid).delete()
            mAuth.currentUser?.delete()
            returnError(null)
        }catch (exception: Exception){
            returnError(exception.localizedMessage)
        }
    }

    override fun updatePLan(userId: String, plan: String, performedTask: (Boolean) -> Unit) {
        val reference = database.collection(collectionPath).document(uid)
        reference.update(mapOf("plan" to plan)).addOnCompleteListener {
            if(it.isSuccessful){
                performedTask(true)
            }else{
                performedTask(false)
            }
        }
    }

    override fun authenticateUser(email: String, password: String, returnUserStatus: (String) -> Unit){
        if(mAuth != null){
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    returnUserStatus("logged")
                }
                .addOnFailureListener {
                    returnUserStatus(it.localizedMessage!!)
                }
            }
    }
}