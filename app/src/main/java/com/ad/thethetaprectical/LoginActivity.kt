package com.ad.thethetaprectical

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.ad.thethetaprectical.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onStart() {
        super.onStart()
        if (AppPreferences.isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            edtEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                override fun afterTextChanged(editable: Editable) {
                    if (editable.isEmpty()) {
                        emailInputLayout.error = "Please enter valid email address"
                    } else {
                        emailInputLayout.error = null
                    }
                }
            })
            edtPass.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                override fun afterTextChanged(editable: Editable) {
                    if (editable.isEmpty()) {
                        passwordInputLayout.error = "Please enter valid password"
                    } else {
                        passwordInputLayout.error = null
                    }
                }
            })

            btnLogin.setOnClickListener {
                if (!Util.isValidEmail(edtEmail.text.toString().trim())) {
                    emailInputLayout.error = "Please enter valid email address"
                    return@setOnClickListener
                }
                if (!Util.isValidPassword(edtPass.text.toString().trim())) {
                    passwordInputLayout.error =
                        "password contain One capital letter One number One symbol (@,\$,%,&,#,) whatever normal symbols that are acceptable"
                    return@setOnClickListener
                }
                AppPreferences.apply {
                    isLogin = true
                    email = edtEmail.text.toString().trim()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finishAffinity()
                }

            }

        }
    }
}