package com.project.usychol.implementations

import com.project.usychol.api.interfaces.PatientEndpoint
import com.project.usychol.api.utils.Connection
import com.project.usychol.data.dao.PatientDAO
import com.project.usychol.domain.entities.Patient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PatientImplementation (): PatientDAO {
    private var retrotifClient: Retrofit
    private var endpoint: PatientEndpoint

    init {
        retrotifClient = Connection.getRetrofitInstance()
        endpoint = retrotifClient.create(PatientEndpoint::class.java)
    }

    override fun create(userId: String, patient: Patient, res: (Patient?) -> Unit) {
        endpoint.postPatient(userId, patient).enqueue(object: Callback<Patient> {
            override fun onResponse(call: Call<Patient>, response: Response<Patient>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Patient>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun findAll(userId: String, res: (ArrayList<Patient>?) -> Unit) {
        endpoint.getPatients(userId).enqueue(object: Callback<ArrayList<Patient>> {
            override fun onResponse(call: Call<ArrayList<Patient>>, response: Response<ArrayList<Patient>>) {
                val list = ArrayList<Patient>()

                if (response?.body() != null && response.body()!!.size > 0)
                    list.addAll(response.body()!!.toList())

                res(list)
            }

            override fun onFailure(call: Call<ArrayList<Patient>>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun update(userId: String, id: String, patient: Patient, res: (Patient?) -> Unit) {
        endpoint.putPatientById(userId, id, patient).enqueue(object: Callback<Patient> {
            override fun onResponse(call: Call<Patient>, response: Response<Patient>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Patient>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun findById(userId: String, id: String, res: (Patient?) -> Unit){
        endpoint.getPatientById(userId, id).enqueue(object: Callback<Patient> {
            override fun onResponse(call: Call<Patient>, response: Response<Patient>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Patient>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun delete(userId: String, id: String, res: (Patient?) -> Unit) {
        endpoint.deletePatient(userId, id).enqueue(object: Callback<Patient> {
            override fun onResponse(call: Call<Patient>, response: Response<Patient>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Patient>, t: Throwable) {
                res(null)
            }

        })
    }

}