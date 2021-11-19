package com.project.usychol.implementations

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.usychol.data.dao.ReminderDAO
import com.project.usychol.domain.entities.Reminder

class ReminderImplementation : ReminderDAO {
    private val database = Firebase.firestore
    private val collectionPath = "reminders"

    override fun findAll(userId: String, returnReminders: (List<Reminder>?) -> Unit) {
        database.collection(collectionPath).whereEqualTo("fromUser", userId).get()
            .addOnSuccessListener {
                val reminders = it.toObjects(Reminder::class.java).toList()
                returnReminders(reminders)
            }
            .addOnFailureListener {
                returnReminders(null)
            }
    }

    override fun findById(id: String, res: (Reminder?) -> Unit) {

    }

    override fun update(id: String, body: Reminder, res: (Reminder?) -> Unit) {

    }

    override fun create(reminder: Reminder, returnError: (String?) -> Unit) {
        database.collection(collectionPath).document(reminder.id!!).set(reminder)
            .addOnSuccessListener {
                returnError(null)
            }
            .addOnFailureListener {
                returnError(it.localizedMessage)
            }
    }

    override fun delete(id: String, res: (Reminder?) -> Unit) {

    }
}