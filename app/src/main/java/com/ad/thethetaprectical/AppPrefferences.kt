package com.ad.thethetaprectical

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "com.ad.thethetaprectical"
    private const val USER_NAME = "UserName"
    private const val EMAIL = "Email"
    private const val IS_FIRST_RUN = "Logined"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var userName: String?
        get() = preferences.getString(USER_NAME, null)
        set(value) = preferences.edit {
            it.putString(USER_NAME, value)
        }

    var email: String?
        get() = preferences.getString(EMAIL, null)
        set(value) = preferences.edit {
            it.putString(EMAIL, value)
        }

    var isLogin: Boolean

        get() = preferences.getBoolean(IS_FIRST_RUN, false)
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_RUN, value)
        }


    fun clearPref() {
        preferences.edit().clear().apply()
    }

    fun clearLogin() {
        preferences.edit {
            isLogin = false
        }
    }
}