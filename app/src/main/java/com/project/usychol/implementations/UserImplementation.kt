package com.project.usychol.implementations

import com.project.usychol.api.interfaces.UserEndpoint
import com.project.usychol.api.utils.Connection
import com.project.usychol.data.dao.UserDAO
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class UserImplementation(): UserDAO {
    private var retrotifClient: Retrofit
    private var endpoint: UserEndpoint

    init {
        retrotifClient = Connection.getRetrofitInstance()
        endpoint = retrotifClient.create(UserEndpoint::class.java)
    }

    override fun findAll(res: (ArrayList<User>?) -> Unit) {
        endpoint.getUsers().enqueue(object: Callback<ArrayList<User>> {
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                val list = ArrayList<User>()

                if (response?.body() != null && response.body()!!.size > 0)
                    list.addAll(response.body()!!.toList())

                res(list)
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun findById(id: String, res: (User?) -> Unit) {
        endpoint.getUserById(id).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun update(id: String, body: User, res: (User?) -> Unit) {
        endpoint.putUserById(id, body).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun create(body: User, res: (User?) -> Unit) {
        endpoint.postUser(body).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun delete(id: String, res: (User?) -> Unit) {
        endpoint.deleteUser(id).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response?.body() != null)
                    res(response?.body())
                else {
                    res(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                res(null)
            }

        })
    }

    override fun findByEmail(email: String): User? {
        var userByEmail: User? = null
        this.findAll { users ->
           userByEmail = users?.find { user ->
               user.email == email
           }
        }

        return userByEmail
    }

    override fun updatePlan(userId: String, body: User) {
        this.update(userId, body){}
    }
}