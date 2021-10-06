package com.project.usychol.api.interfaces

import com.project.usychol.domain.entities.Report
import retrofit2.Call
import retrofit2.http.*

interface ReportEndpoint {
    @GET("/users/{userId}/patients/{patientId}/reports")
    fun getReports(
        @Path("userId")userId: String,
        @Path("patientId")patientId: String
    ): Call<ArrayList<Report>>

    @GET("/users/{userId}/patients/{patientId}/reports/{id}")
    fun getReportById(
        @Path("userId")userId: String,
        @Path("patientId")patientId: String,
        @Path("id")id: String
    ): Call<Report>

    @PUT("/users/{userId}/patients/{patientId}/reports/{id}")
    fun putReportById(
        @Path("userId")userId: String,
        @Path("patientId")patientId: String,
        @Path("id")id: String,
        @Body body: Report
    ): Call<Report>

    @POST("/users/{userId}/patients/{patientId}/reports")
    fun postReport(
        @Path("userId")userId: String,
        @Path("patientId")patientId: String,
        @Body body: Report): Call<Report>

    @DELETE("/users/{userId}/patients/{patientId}/reports/{id}")
    fun deleteReport(
        @Path("userId")userId: String,
        @Path("patientId")patientId: String,
        @Path("id")id: String
    ): Call<Report>
}