package com.project.usychol.api.interfaces

import com.project.usychol.domain.entities.Reminder
import retrofit2.Call
import retrofit2.http.*

interface ReminderEndpoint {
    @GET("/reminders")
    fun getReminders(): Call<ArrayList<Reminder>>

    @GET("/reminders/{id}")
    fun getReminderById(@Path("id")id: String): Call<Reminder>

    @PUT("/reminders/{id}")
    fun putReminderById(@Path("id")id: String, @Body body: Reminder): Call<Reminder>

    @POST("/reminders")
    fun postReminder(@Body body: Reminder): Call<Reminder>

    @DELETE("/reminders/{id}")
    fun deleteReminder(@Path("id") id: String): Call<Reminder>
}