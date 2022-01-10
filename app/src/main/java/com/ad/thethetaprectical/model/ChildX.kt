package com.ad.thethetaprectical.model

import java.io.Serializable

data class ChildX(
    val age: Int,
    val child: List<ChildXX>,
    val email: String,
    val id: String,
    val level: Int,
    val name: String,
    val parent: String
): Serializable