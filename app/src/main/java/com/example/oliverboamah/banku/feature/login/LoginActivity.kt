package com.example.oliverboamah.banku.feature.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chootdev.csnackbar.Type

import com.example.oliverboamah.banku.R

import com.example.oliverboamah.banku.feature.lib.App
import com.example.oliverboamah.banku.feature.model.Model
import com.example.oliverboamah.banku.feature.route.Route
import com.example.oliverboamah.banku.feature.util.PreferencesUtil
import com.fingers.fingers.lib.InputValidation
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        model = Model(SignUpActivity@ this)
    }

    fun launchSignUp(view: View) {
        Route.launchSignUpActivity(this)
    }

    fun login(view: View) {
        when (InputValidation.validateLoginForm(txtMobileNumber, txtPin)) {
            true -> {
                val userIndex = model.getUserIndex(txtMobileNumber.text.toString().trim(),
                        txtPin.text.trim().toString())

                when (userIndex > -1) {
                    true -> {
                        PreferencesUtil.setNoMoreFirstTimer(this)
                        Route.launchHomeActivity(this, userIndex)
                    }
                    else -> App.displayAlert(this, Type.ERROR, R.string.invalid_login_detail)
                }
            }
        }
    }
}
