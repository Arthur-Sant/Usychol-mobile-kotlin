package com.project.usychol.implementations

import com.project.usychol.api.interfaces.ReportEndpoint
import com.project.usychol.api.utils.Connection
import com.project.usychol.data.dao.ReportDAO
import com.project.usychol.domain.entities.Report
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ReportImplementation(): ReportDAO {
    private var retrotifClient: Retrofit
    private var endpoint: ReportEndpoint

    init {
        retrotifClient = Connection.getRetrofitInstance()
        endpoint = retrotifClient.create(ReportEndpoint::class.java)
    }

    override fun create(userId: String, patientId: String, report: Report, res: (Report?) -> Unit) {
        endpoint.postReport(userId, patientId, report).enqueue(object: Callback<Report> {
            override fun onResponse(call: Call<Report>, response: Response<Report>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Report>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun findById(userId: String, patientId: String, id: String, res: (Report?) -> Unit) {
        endpoint.getReportById(userId, patientId, id).enqueue(object: Callback<Report> {
            override fun onResponse(call: Call<Report>, response: Response<Report>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Report>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun update(
        userId: String,
        patientId: String,
        id: String,
        report: Report,
        res: (Report?) -> Unit
    ) {
        endpoint.putReportById(userId, patientId, id, report).enqueue(object: Callback<Report> {
            override fun onResponse(call: Call<Report>, response: Response<Report>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Report>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun delete(userId: String, patientId: String, id: String, res: (Report?) -> Unit) {
        endpoint.deleteReport(userId, patientId, id).enqueue(object: Callback<Report> {
            override fun onResponse(call: Call<Report>, response: Response<Report>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Report>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun findAll(
        userId: String,
        patientId: String,
        id: String,
        res: (ArrayList<Report>?) -> Unit
    ) {
        endpoint.getReports(userId, patientId).enqueue(object: Callback<ArrayList<Report>> {
            override fun onResponse(call: Call<ArrayList<Report>>, response: Response<ArrayList<Report>>) {
                val list = ArrayList<Report>()

                if (response?.body() != null && response.body()!!.size > 0)
                    list.addAll(response.body()!!.toList())

                res(list)
            }

            override fun onFailure(call: Call<ArrayList<Report>>, t: Throwable) {
                res(null)
            }

        })
    }

}