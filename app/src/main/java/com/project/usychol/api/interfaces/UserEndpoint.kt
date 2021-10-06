package com.project.usychol.api.interfaces

import com.project.usychol.domain.entities.User
import retrofit2.Call
import retrofit2.http.*

interface UserEndpoint {
    @GET("/users")
    fun getUsers(): Call<ArrayList<User>>

    @GET("/users/{id}")
    fun getUserById(@Path("id")id: String): Call<User>

    @PUT("/users/{id}")
    fun putUserById(@Path("id")id: String, @Body body: User): Call<User>

    @POST("/users")
    fun postUser(@Body body: User): Call<User>

    @DELETE("/users/{id}")
    fun deleteUser(@Path("id") id: String): Call<User>
}