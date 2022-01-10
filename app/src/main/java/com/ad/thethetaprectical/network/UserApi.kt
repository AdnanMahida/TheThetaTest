package com.ad.thethetaprectical.network

import com.ad.thethetaprectical.model.UsersItem
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {
    @GET("/")
    suspend fun getUsers() : Response<List<UsersItem>>
}