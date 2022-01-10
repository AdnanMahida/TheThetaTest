package com.ad.thethetaprectical.model

import java.io.Serializable

data class Child(
    val age: Int,
    val child: List<ChildX>,
    val email: String,
    val id: String,
    val level: Int,
    val name: String,
    val parent: String
): Serializable