package com.ad.thethetaprectical.model

import java.io.Serializable

data class UsersItem(
    val age: Int,
    val child: List<Child>,
    val email: String,
    val id: String,
    val level: Int,
    val name: String,
    val parent: String
) : Serializable