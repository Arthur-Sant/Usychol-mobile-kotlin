package com.project.usychol.api.interfaces

import com.project.usychol.domain.entities.Patient
import com.project.usychol.domain.entities.Report
import retrofit2.Call
import retrofit2.http.*

interface PatientEndpoint {
    @GET("/users/{userId}/patients")
    fun getPatients(@Path("userId")userId: String): Call<ArrayList<Patient>>

    @GET("/users/{userId}/patients/{id}")
    fun getPatientById(
        @Path("userId")userId: String,
        @Path("id")id: String
    ): Call<Patient>

    @PUT("/users/{userId}/patients/{id}")
    fun putPatientById(
        @Path("userId")userId: String,
        @Path("id")id: String,
        @Body body: Patient
    ): Call<Patient>

    @POST("/users/{userId}/patients")
    fun postPatient(
        @Path("userId")userId: String,
        @Body body: Patient
    ): Call<Patient>

    @DELETE("/users/{userId}/patients/{id}")
    fun deletePatient(
        @Path("userId")userId: String,
        @Path("id")id: String
    ): Call<Patient>
}