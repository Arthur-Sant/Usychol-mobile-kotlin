package com.project.usychol.implementations

import com.project.usychol.api.interfaces.ReminderEndpoint
import com.project.usychol.api.utils.Connection
import com.project.usychol.data.dao.ReminderDAO
import com.project.usychol.domain.entities.Reminder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ReminderImplementation : ReminderDAO {
    private var retrotifClient: Retrofit
    private var endpoint: ReminderEndpoint

    init {
        retrotifClient = Connection.getRetrofitInstance()
        endpoint = retrotifClient.create(ReminderEndpoint::class.java)
    }

    override fun findAll(res: (ArrayList<Reminder>?) -> Unit) {
        endpoint.getReminders().enqueue(object: Callback<ArrayList<Reminder>> {
            override fun onResponse(call: Call<ArrayList<Reminder>>, response: Response<ArrayList<Reminder>>) {
                val list = ArrayList<Reminder>()

                if (response?.body() != null && response.body()!!.size > 0)
                    list.addAll(response.body()!!.toList())

                res(list)
            }

            override fun onFailure(call: Call<ArrayList<Reminder>>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun findById(id: String, res: (Reminder?) -> Unit) {
        endpoint.getReminderById(id).enqueue(object: Callback<Reminder> {
            override fun onResponse(call: Call<Reminder>, response: Response<Reminder>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Reminder>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun update(id: String, body: Reminder, res: (Reminder?) -> Unit) {
        endpoint.putReminderById(id, body).enqueue(object: Callback<Reminder> {
            override fun onResponse(call: Call<Reminder>, response: Response<Reminder>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Reminder>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun create(reminder: Reminder, res: (Reminder?) -> Unit) {
        endpoint.postReminder(reminder).enqueue(object: Callback<Reminder> {
            override fun onResponse(call: Call<Reminder>, response: Response<Reminder>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Reminder>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun delete(id: String, res: (Reminder?) -> Unit) {
        endpoint.deleteReminder(id).enqueue(object: Callback<Reminder> {
            override fun onResponse(call: Call<Reminder>, response: Response<Reminder>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<Reminder>, t: Throwable) {
                res(null)
            }

        })
    }
}